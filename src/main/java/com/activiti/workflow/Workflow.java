package com.activiti.workflow;

import com.activiti.workflow.pro.WorkflowBean;
import com.activiti.workflow.service.ProcessService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybUser;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.EnumUtils;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.json.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;


@Controller
@Scope("prototype")
@RequestMapping("/workflow")
public class Workflow {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RepositoryService repositoryService;
    @Resource
    TaskService taskService;
    @Resource
    ProcessService processService;
    @Resource
    HistoryService historyService;
   /**
    * @Author TYB
    * @Date 2018-01-10 下午 4:36
    * @Desc 创建流程模型
    */
    @RequestMapping(value = "create")
    public void create(String name,String key,String description,HttpServletRequest request, HttpServletResponse response) {
        try {
            name=name==null?"name":name;
            key=key==null?"key":key;
            description=description==null?"description":description;
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            //response.sendRedirect(request.getContextPath() + "/service/editor?id=" + modelData.getId());
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            //logger.error("创建模型失败：", e);
            System.out.println("创建模型失败：");
            e.printStackTrace();
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-10 下午 4:36
     * @Desc 修改编辑流程模型
     */
    @RequestMapping(value = "updateModel")
    public void create(HttpServletResponse response,HttpServletRequest request,String modelId) {
        try {
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelId);
        } catch (Exception e) {
            System.out.println("创建模型编辑失败：");
            e.printStackTrace();
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-10 下午 5:30
     * @Desc 流程模型列表
     */
    @RequestMapping(value = "modelList")
    public ModelAndView modelList(String m_name,String pageNo) {
        ModelAndView mav = new ModelAndView("left/process_model_list");
        Pager pager = new Pager();
        pager.setPageNum((pageNo==null?0:Integer.parseInt(pageNo) - 1) * pager.getPageSize());
        long count = 0;
        List<Model> list;
        if(StringUtils.isNotEmpty(m_name)){
            count=repositoryService.createModelQuery().modelNameLike("%"+m_name+"%").count();
            list = repositoryService.createModelQuery()//创建流程查询实例
                    .orderByCreateTime().desc()//降序
                    .modelNameLike("%"+m_name+"%")//根据Name模糊查询
                    .listPage(pager.getPageNum(),pager.getPageSize());
        }else {
            count=repositoryService.createModelQuery().count();
            list = repositoryService.createModelQuery()//创建流程查询实例
                    .orderByCreateTime().desc()//降序
                    .listPage(pager.getPageNum(),pager.getPageSize());
        }
        //取得总数量
        pager.setTotalNum(Integer.parseInt(count+""));
        pager.setPageList(list);
        mav.addObject("pager",pager);
        return mav;
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 11:27
     * @Desc 根据模型Model部署流程
     */
    @RequestMapping(value = "deploy/{modelId}",produces = "application/json")
    public @ResponseBody AjaxDone deploy(@PathVariable("modelId") String modelId, RedirectAttributes redirectAttributes) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).category("aa").addString(processName, new String(bpmnBytes,"utf-8")).deploy();
            redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
            return AjaxDone.success(EnumUtils.BSLCCG+deployment.getId());
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
            //e.printStackTrace();
            return AjaxDone.error(EnumUtils.BSLCSB+modelId);
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 11:52
     * @Desc 删除流程模型
     */
    @RequestMapping(value = "delete/{modelId}",produces = "application/json")
    public @ResponseBody AjaxDone delete(@PathVariable("modelId") String modelId) {
        try {
            repositoryService.deleteModel(modelId);
            return AjaxDone.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 11:52
     * @Desc 导出流程模型的xml文件
     */
    @RequestMapping(value = "exportModelByXML")
    public void exportModelByXML(String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}", modelId, e);
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 11:52
     * @Desc 显示流程模型的xml文件
     */
    @RequestMapping(value = "showProcessModelXML")
    public @ResponseBody  void showProcessModelXML(String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            OutputStream out=response.getOutputStream();
            for(int b=-1;(b=in.read())!=-1;){
                out.write(b);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            logger.error("显示model的xml文件失败：modelId={}", modelId, e);
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 6:52
     * @Desc 跳转-查看流程图
     */
    @RequestMapping( value = "toShowViewProcess")
    public ModelAndView toShowView(String deploymentId,String diagramResourceName,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/left/process_showView");
        String modelId = request.getParameter("modelId");//流程模型ID
        String taskId = request.getParameter("taskId");//流程模型ID
        if (StringUtils.isNotEmpty(modelId)){
            mv.addObject("modelId",modelId);
        }else if (StringUtils.isNotEmpty(taskId)){
            mv.addObject("taskId",taskId);
        }else {
            mv.addObject("deploymentId",deploymentId);
            mv.addObject("diagramResourceName",diagramResourceName);
        }
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 6:52
     * @Desc 查看流程图
     */
    @RequestMapping( value = "showViewProcess",produces = "application/json")
    public void showView(String deploymentId, String diagramResourceName, HttpServletResponse response,HttpServletRequest request)throws Exception{
        String modelId = request.getParameter("modelId");//流程模型ID
        String taskId = request.getParameter("taskId");//任务模型ID
        InputStream inputStream;
        if (StringUtils.isNotEmpty(modelId)) {//流程模型管理 显示流程图
            byte[] modelEditorSourceExtra = repositoryService.getModelEditorSourceExtra(modelId);
            inputStream=new ByteArrayInputStream(modelEditorSourceExtra);
        }else if (StringUtils.isNotEmpty(taskId)) {//流程模型管理 显示流程图

            inputStream=processService.currentFlowchart(taskId);
        }else {//流程定义管理  显示流程图
            inputStream = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
        }
        OutputStream out=response.getOutputStream();
        for(int b=-1;(b=inputStream.read())!=-1;){
            out.write(b);
        }
        out.flush();
        out.close();
        inputStream.close();
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 5:46
     * @Desc 查询流程定义管理
     */
    @RequestMapping(value = "processDefinitionPage")
    public ModelAndView processDefinitionPage(String p_name, String pageNo){
        ModelAndView mv = new ModelAndView();
        p_name=p_name==null?"":p_name;
        Pager pager = new Pager();
        pager.setPageNum((pageNo==null?0:Integer.parseInt(pageNo) - 1) * pager.getPageSize());
        long count = 0;
        List<ProcessDefinition> processDefinitionList = null;
        if(StringUtils.isNotEmpty(p_name)){
            count=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%"+p_name+"%").count();
            processDefinitionList=repositoryService.createProcessDefinitionQuery()//创建流程查询实例
                    .orderByDeploymentId().desc() //降序
                    .processDefinitionNameLike("%"+p_name+"%")//根据Name模糊查询
                    .listPage(pager.getPageNum(),pager.getPageSize());
        }else {
            count=repositoryService.createProcessDefinitionQuery().count();
            processDefinitionList=repositoryService.createProcessDefinitionQuery()//创建流程查询实例
                    .orderByDeploymentId().desc() //降序
                    .listPage(pager.getPageNum(),pager.getPageSize());
        }
        //取得总数量
        pager.setTotalNum(Integer.parseInt(count+""));
        pager.setPageList(processDefinitionList);
        mv.addObject("pager",pager);
        mv.setViewName("left/process_dy_list");
       return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 6:21
     * @Desc 查询流程部署管理
     */
    @RequestMapping(value = "processDeployPage")
    public ModelAndView deployPage(String pageNo,String p_name){
        ModelAndView mv = new ModelAndView();
        p_name=p_name==null?"":p_name;
        Pager pager = new Pager();
        pager.setPageNum((pageNo==null?0:Integer.parseInt(pageNo) - 1) * pager.getPageSize());
        List<Deployment> deployList;
        long deployCount;

        if (StringUtils.isNotEmpty(p_name)) {
            //取得总数量
            deployCount=repositoryService.createDeploymentQuery().deploymentNameLike("%"+p_name+"%")
                    .count();
            deployList = repositoryService.createDeploymentQuery()//创建流程查询实例
                    .orderByDeploymenTime().desc()  //降序
                    .deploymentNameLike("%"+p_name+"%")   //根据Name模糊查询
                    .listPage(pager.getPageNum(),pager.getPageSize());
        } else {
            //取得总数量
            deployCount=repositoryService.createDeploymentQuery().count();
            deployList = repositoryService.createDeploymentQuery()//创建流程查询实例
                    .orderByDeploymenTime().desc()  //降序
                    .listPage(pager.getPageNum(),pager.getPageSize());
        }
        pager.setTotalNum(Integer.parseInt(deployCount+""));
        pager.setPageList(deployList);
        mv.addObject("pager",pager);
        mv.setViewName("left/process_deploy_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 10:48
     * @Desc 删除部署的流程
     */
    @RequestMapping(value = "/delDeploy")
    public @ResponseBody AjaxDone delDeploy(HttpServletResponse response, String ids) throws Exception{
        //拆分字符串
        String[] idsStr=ids.split(",");
        try {
            for(String str:idsStr){
                repositoryService.deleteDeployment(str, true);
            }
            return AjaxDone.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error();
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 3:23
     * @Desc 代办任务管理
     */
    @RequestMapping(value = "/taskPage")
    public ModelAndView taskPage(String pageNo,String s_name,HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView();
        s_name=s_name==null?"":s_name;
        Pager pager = new Pager();
        pager.setPageNum((pageNo==null?0:Integer.parseInt(pageNo) - 1) * pager.getPageSize());
        TybUser user = (TybUser) request.getSession().getAttribute("loginUser");
        long total;
        List<Task> taskList;
        if (StringUtils.isNotEmpty(s_name)) {
            taskList = taskService.createTaskQuery()
                    //.taskCandidateGroup(userId)// 根据用户id查询
                    .taskNameLike("%"+s_name+"%")// 根据任务名称查询
                    .taskAssignee(user.getUserId()+"")//根据用户查询
                    // 返回带分页的结果集合
                    .listPage(pager.getPageNum(),pager.getPageSize());
            total = taskService.createTaskQuery()
                    .taskNameLike("%"+s_name+"%")
                    .taskAssignee(user.getUserId()+"")
                    .count(); // 获取总记录数
        } else {
            //有想法的话，可以去数据库观察  ACT_RU_TASK 的变化
            taskList = taskService.createTaskQuery()
                    .taskAssignee(user.getUserId()+"")
                    .listPage(pager.getPageNum(),pager.getPageSize());// 返回带分页的结果集合
            total = taskService.createTaskQuery().taskAssignee(user.getUserId()+"").count(); // 获取总记录数
        }
        pager.setTotalNum(Integer.parseInt(total+""));
        pager.setPageList(taskList);
        mv.addObject("pager",pager);
        mv.setViewName("left/process_agency_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-22 下午 2:00
     * @Desc 已办理任务管理
     */
    @RequestMapping(value = "/finishedList")
    public ModelAndView finishedList(String pageNo,String s_name,HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView();
        s_name=s_name==null?"":s_name;
        Pager pager = new Pager();
        pager.setPageNum((pageNo==null?0:Integer.parseInt(pageNo) - 1) * pager.getPageSize());
        TybUser user = (TybUser) request.getSession().getAttribute("loginUser");
        long total;
        List<HistoricTaskInstance> histList;
        if (StringUtils.isNotEmpty(s_name)) {
            histList=historyService.createHistoricTaskInstanceQuery()
                    //.taskCandidateGroup(groupId)//根据角色名称查询
                    .taskAssignee(user.getUserId()+"")
                    .taskDeleteReason("completed")
                    .taskNameLike("%"+s_name+"%")
                    .listPage(pager.getPageNum(),pager.getPageSize());
            total = historyService.createHistoricTaskInstanceQuery()
                    //.taskCandidateGroup(groupId)
                    .taskAssignee(user.getUserId()+"")
                    .taskDeleteReason("completed")
                    .taskNameLike("%"+s_name+"%")
                    .count();// 获取总记录数
        } else {
            histList=historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(user.getUserId()+"")
                    .taskDeleteReason("completed")
                    .listPage(pager.getPageNum(),pager.getPageSize());
            total = historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(user.getUserId()+"")
                    .taskDeleteReason("completed")
                    .count();// 获取总记录数
        }
        pager.setTotalNum(Integer.parseInt(total+""));
        pager.setPageList(histList);
        mv.addObject("pager",pager);
        mv.setViewName("left/process_finished_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-22 下午 9:32
     * @Desc 根据任务id查询流程实例的具体执行过程
     */
    @RequestMapping("/executionProcess")
    public ModelAndView executionProcess(String taskId) throws Exception{
        ModelAndView mv = new ModelAndView();
        HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        String processInstanceId=hti.getProcessInstanceId(); // 获取流程实例id
        List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        mv.addObject("haiList",haiList);
        mv.setViewName("left/process_execution_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-22 下午 9:44
     * @Desc 根据任务id查询历史批注
     */
    @RequestMapping("/historyCommentList")
    public ModelAndView historyCommentList(String taskId) throws Exception{
        ModelAndView mv = new ModelAndView();
        HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .singleResult();
        List<Comment> comment=null;
        if(hti!=null){
            comment=taskService.getProcessInstanceComments(hti.getProcessInstanceId());
        }
        mv.addObject("comment",comment);
        mv.setViewName("left/process_historycomment_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-22 下午 10:30
     * @Desc 根据表单中ProcessInstanceId 查询批注 在表单处查询
     */
    @RequestMapping("/historyCommentFormList")
    public ModelAndView historyCommentFormList(String piId) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Comment> comment=taskService.getProcessInstanceComments(piId);
        mv.addObject("comment",comment);
        mv.setViewName("left/process_historycomment_list");
        return mv;
    }
    /**
     * @Author TYB
     * @Date 2018-01-23 下午 10:33
     * @Desc 打开任务表单  页面点击开始办理
     */
    public String viewTaskForm(WorkflowBean wb){
        //获取任务ID
        String taskId = wb.getTaskId();
        //从任务的活动节点中，获取formkey值，此时是一个url连接
        String url = processService.findTaskFormKey(taskId);
        //使用任务ID，获取流程对应业务的主键id（请假单id）
        String id = processService.findBusinessKeyByTaskId(wb.getTaskId());
        url += "?id="+id+"&taskId="+taskId;
        return url;
    }
}
