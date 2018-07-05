package com.activiti.workflow.service.impl;

import com.activiti.utils.ProcessEnum;
import com.activiti.workflow.pro.WorkflowBean;
import com.activiti.workflow.service.ProcessService;
import com.taoyb.simon.web.model.TybLeave;
import com.taoyb.simon.web.service.TybLeaveService;
import com.taoyb.simon.web.utils.EnumUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2018-01-15.
 */
@Service
public class ProcessServiceImpl implements ProcessService {
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    HistoryService historyService;
    @Autowired
    FormService formService;
    @Resource
    TybLeaveService tybLeaveService;

    @Override
    public ProcessInstance start(String type,WorkflowBean wb) throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessEnum.model.toString(), wb.getUserId());
        //variables.put(ProcessVariableEnum.agent.toString(), "");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(type,wb.getId().toString(), variables);//根据Key启动流程实例 默认根据最高版本启动
        // 根据流程实例Id查询任务
        return pi;
    }
    @Override
    public void complete(String piId){
        Task task = taskService.createTaskQuery().processInstanceId(piId).singleResult();
        taskService.complete(task.getId());// 完成请假单任务
    }

    @Override
    public void saveSubmitTask(WorkflowBean workflowBean) {
        //获取任务ID
        String taskId = workflowBean.getTaskId();
        //请假单ID
        Long id = workflowBean.getId();
        //连线名称
        String outcome = workflowBean.getOutcome();
        //获取页面中的批注
        String comment = workflowBean.getComment();
        //使用请假单ID，查询请假单的信息
        TybLeave leave = tybLeaveService.findById(id);
        /**
         * 1：从页面中获取连线的名称（点击按钮的名称）
         * 使用连线的名称，设置流程变量，此时才可以实现完成任务的同时，指定哪条连线离开
         * 流程变量的名称outcome，流程变量的值：页面获取的连线名称
         */
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProcessEnum.outcome.getValue(), outcome);

        //使用任务ID，查询任务对象（从而获取流程实例ID）
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();

        //2:完成任务之前，获取页面的批注信息，添加一个批注
        /**
         * 提供一个表（act_hi_comment），专门存储对应流程的审批记录
         操作，向act_hi_comment表中添加数据
         */
        //参数从Session中获取当前登录人，与审核人绑定（保证了审核的线程安全）
        //String authenticatedUserId = SessionContext.get().getName();
        Authentication.setAuthenticatedUserId(workflowBean.getUserId());//当前登录人与审批人进行绑定
        taskService.addComment(taskId, processInstanceId, comment);//其中参数3表示批注
        //3：指定任务ID，并设置流程变量，完成任务
        taskService.complete(taskId, variables);
        //4：完成任务后，到达下一个任务，在任务节点中使用Listener，实现指定下一个任务的办理人（已经实现在ManagerTaskHandler类中）
        //5：如果完成任务后，流程已经结束，此时更新请假单表中的state状态，从1变成2（2表示审核完成）
        //判断流程是否结束

        //获取流程实例对象
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        //此时流程实例结束了
        if(pi==null){
            //此时更新请假单表中的state状态，从1变成2（2表示审核完成）
            leave.setLeaveState(EnumUtils.LCSHTG.getValue());
            tybLeaveService.updateEntity(leave);
        }

    }

    @Override
    public String findBusinessKeyByTaskId(String taskId) {
        //1:使用任务ID，查询任务对象（从而获取流程实例ID）
        Task task = taskService.createTaskQuery()
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //流程实例ID
        String piId = task.getProcessInstanceId();
        //2:使用流程实例ID，查询正在执行的执行对象表，获取流程实例的对象（从而获取BUSINIESS_KEY对应的值）
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(piId)//按照流程实例ID查询
                .singleResult();
        //获取业务key的值(LeaveBill.2)
        String businessKey = pi.getBusinessKey();
        /*//请假单ID
        String id = "";
        if(StringUtils.isNotBlank(businessKey)){
            //截取字符串，小数点后的那个值
            id = businessKey.split("\\.")[1];
        }*/
        return businessKey;
    }

    @Override
    public List<Comment> findCommentListByTaskId(String taskId) {
        //返回的集合批注信息
        List<Comment> list = new ArrayList<Comment>();
        //使用任务ID，查询任务对象（从而获取流程实例ID）
        Task task = taskService.createTaskQuery()//
                .taskId(taskId)//使用任务ID查询
                .singleResult();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //1：使用任务ID，查找历史的活动表，获取每个执行完成的任务ID
        List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .activityType("userTask")//按照历史活动节点类型是userTask查询
                .list();
        //遍历，获取当前流程实例中的每个任务ID
        if(haiList!=null && haiList.size()>0){
            for(HistoricActivityInstance hai:haiList){
                String hisTaskId = hai.getTaskId();
                //获取每个历史任务ID，对应的批注信息
                List<Comment> commentList = taskService.getTaskComments(hisTaskId);
                if(commentList!=null && commentList.size()>0){
                    for(Comment c:commentList){
                        list.add(c);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public InputStream currentFlowchart(String taskId) throws IOException {
        Task task = taskService.createTaskQuery() // 创建任务查询
                .taskId(taskId) // 根据任务id查询
                .singleResult();
        String processDefinitionId = "";
        String processInstanceId = "";
        if (task== null){
            HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery()
                    .taskId(taskId)
                    .singleResult();
            processDefinitionId = hti.getProcessDefinitionId();
            processInstanceId = hti.getProcessInstanceId();
        }else {
            processDefinitionId = task.getProcessDefinitionId();
            processInstanceId = task.getProcessInstanceId();
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);
        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }
        //中文显示的是口口口，设置字体就好了
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0);
        //单独返回流程图，不高亮显示
        //InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
        // 输出资源内容到相应对象
        return imageStream;
    }

    @Override
    public List<String> getButtonsForTransition(String taskId) {
        TaskQuery createTaskQuery = taskService.createTaskQuery();
        TaskQuery q = createTaskQuery.taskId(taskId);
        Task task = q.singleResult();
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        ExecutionEntity exe = (ExecutionEntity) runtimeService
                .createExecutionQuery().executionId(task.getExecutionId())
                .singleResult();
        ActivityImpl activity = pde.findActivity(exe.getActivityId());
        List<PvmTransition> transitions = activity.getOutgoingTransitions();
        List<String> buttons = new ArrayList<String>();
        for (PvmTransition pvmTransition : transitions) {
            buttons.add(pvmTransition.getProperty("name").toString());
        }
        return buttons;
    }

    @Override
    public void getVariables(String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
    }

    @Override
    public void getVariablesByExecution(String executionId) {
        runtimeService.getVariables(executionId);
    }

    @Override
    public String findTaskFormKey(String taskId) {
        TaskFormData formData = formService.getTaskFormData(taskId);
        String url = formData.getFormKey();
        return url;
    }

    /**
     * @Author TYB
     * @Date 2018-01-15 下午 7:07
     * @Desc 获取需要高亮的线
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
