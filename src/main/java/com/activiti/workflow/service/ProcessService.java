package com.activiti.workflow.service;

import com.activiti.workflow.pro.WorkflowBean;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by taoyb on 2018-01-15.
 * 业务流程服务接口
 */
public interface ProcessService {
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 4:31
     * @Desc 开始流程
     */
    public ProcessInstance start(String type, WorkflowBean wb)throws Exception;
    /**
     * @Author TYB
     * @Date 2018-01-17 下午 1:31
     * @Desc 完成任务节点
     */
    public void complete(String taskId);
    /**
     * @Author TYB
     * @Date 2018-01-18 下午 8:04
     * @Desc 提交流程审批 点击审批，完成任务
     */
    public void saveSubmitTask(WorkflowBean workflowBean);
    /**
     * @Author TYB
     * @Date 2018-01-18 下午 10:26
     * @Desc 根据任务节点Id查询关联的表单Id
     */
    public String findBusinessKeyByTaskId(String taskId);
    /**
     * @Author TYB
     * @Date 2018-01-18 下午 10:33
     * @Desc 查询审批记录表单  提交的批注
     */
    public List<Comment> findCommentListByTaskId(String taskId);
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 4:30
     * @Desc 查看当前流程图
     */
    public InputStream currentFlowchart(String taskId) throws IOException;
    /**
     * @Author TYB
     * @Date 2018-01-15 下午 11:27
     * @Desc 动态获取办理按钮
     */
    public List<String> getButtonsForTransition(String taskId);
    /**
     * @Author TYB
     * @Date 2018-01-17 下午 1:33
     * @Desc 根据任务获取流程变量
     */
    public void getVariables(String taskId);
    /**
     * @Author TYB
     * @Date 2018-01-17 下午 1:35
     * @Desc 根据流程实例获取流程变量
     */
    public void getVariablesByExecution(String executionId);
    /**
     * @Author TYB
     * @Date 2018-01-23 下午 10:23
     * @Desc 从任务的节点中获取FormKey的值
     */
    public String findTaskFormKey(String taskId);
}
