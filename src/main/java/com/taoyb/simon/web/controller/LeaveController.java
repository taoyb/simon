package com.taoyb.simon.web.controller;


import com.activiti.utils.ProcessEnum;
import com.activiti.workflow.Workflow;
import com.activiti.workflow.pro.WorkflowBean;
import com.activiti.workflow.service.ProcessService;
import com.taoyb.simon.common.utils.Pager;
import com.taoyb.simon.web.model.TybLeave;
import com.taoyb.simon.web.utils.AjaxDone;
import com.taoyb.simon.web.utils.BaseController;
import com.taoyb.simon.web.utils.EnumUtils;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-05.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController extends BaseController {
    @Resource
    ProcessService processService;

    /**
     * @Author TYB
     * @Date 2018-01-09 下午 2:16
     * @Desc 查询所有请假单
     */
    @RequestMapping(value = "/findAll")
    public String formAll(Model model, TybLeave leave) {
        Pager<TybLeave> pager = tybLeaveService.findAllLeaves(leave, 1, 15);
        model.addAttribute("pager", pager);
        model.addAttribute("m", request.getParameter("m"));
        return "left/leave_all";
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 2:16
     * @Desc 跳往申请请假单
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd(Model model,String mid) {
        if (StringUtils.isNotEmpty(mid)){
            TybLeave leave = tybLeaveService.findById(Long.parseLong(mid));
            model.addAttribute("leave",leave);
        }
        return "left/leave_add";
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 下午 2:16
     * @Desc 提交请假表单
     */
    @RequestMapping(value = "/leaveAdd",method = RequestMethod.POST)
    public @ResponseBody
    AjaxDone leaveAdd(TybLeave leave) {
        return tybLeaveService.saveOrUpdateLeave(leave);
    }
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 11:57
     * @Desc 删除请假表单
     */
    @RequestMapping(value = "/deleteLeave",method = RequestMethod.POST)
    public @ResponseBody
    AjaxDone deleteLeave(Long mid) {
        return tybLeaveService.deleteLeave(mid);
    }
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 2:01
     * @Desc 提交请假流程
     */
    @RequestMapping("/startApply")
    public @ResponseBody AjaxDone startApply(HttpServletResponse response, WorkflowBean wb) throws Exception{
        try {
            ProcessInstance pi = processService.start(ProcessEnum.LEAVE.getValue(), wb);
            processService.complete(pi.getId());
            TybLeave leave = tybLeaveService.findById(wb.getId());
            leave.setLeaveState(EnumUtils.LCSHZ.getValue());
            leave.setProcessInstanceId(pi.getProcessInstanceId());
            tybLeaveService.updateEntity(leave);
            return AjaxDone.success("请假申请提交成功，目前审核中，请耐心等待！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error("请假申请提交失败！");
        }
    }
    /**
     * @Author TYB
     * @Date 2018-01-18 下午 10:30
     * @Desc 跳转到任务办理页面
     */
    @RequestMapping(value = "toLeaveSp",method = RequestMethod.GET)
    public String toLeaveSp(Model model,WorkflowBean wb){
        wb.setUserId(getLoginUser().getUserId()+"");
        String businessKey = processService.findBusinessKeyByTaskId(wb.getTaskId());
        TybLeave leave = tybLeaveService.findById(Long.parseLong(businessKey));
        List<String> btns = processService.getButtonsForTransition(wb.getTaskId());
        List<Comment> comments = processService.findCommentListByTaskId(wb.getTaskId());
        model.addAttribute("leave",leave);
        model.addAttribute("btns",btns);
        model.addAttribute("comments",comments);
        model.addAttribute("wb",wb);
        return "left/leave_sp";
    }
    @RequestMapping(value = "/submitTask",method = RequestMethod.POST)
    public @ResponseBody AjaxDone submitTask(HttpServletResponse response, WorkflowBean wb) throws Exception{
        try {
            wb.setUserId(getLoginUser().getRealName());
            processService.saveSubmitTask(wb);
            return AjaxDone.success("请假申请提交成功，目前审核中，请耐心等待！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxDone.error("请假申请提交失败！");
        }
    }
}
