package com.taoyb.simon;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by taoyb on 2018-01-09.
 */
public class Flow {
    /**
     * @Author TYB
     * @Date 2018-01-09 上午 11:50
     * @Desc 1.把流程部署到流程引擎中
     */
    @Test
    public void deploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        /*
        processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("com/activiti/workflow/helloworld.bpmn")
                .addClasspathResource("com/activiti/workflow/helloworld.png")
                .deploy();*/


        //获取资源相对路径
        String bpmnPath = "D:\\project\\idea\\simon\\src\\main\\java\\com\\activiti\\workflow\\helloworld.bpmn";
        String pngPath = "D:\\project\\idea\\simon\\src\\main\\java\\com\\activiti\\workflow\\helloworld.bpmn";
        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = null;
        FileInputStream pngfileInputStream = null;
        try {
            bpmnfileInputStream = new FileInputStream(bpmnPath);
            pngfileInputStream = new FileInputStream(pngPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("helloworld.bpmn",bpmnfileInputStream)
                .addInputStream("helloworld.png", pngfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }
    /**
     * @Author TYB
     * @Date 2018-01-09 上午 11:54
     * @Desc 2.启动流程实例
     */
    public void StartProcessInstance(){
        String pdKey = "myProcess";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService().startProcessInstanceByKey(pdKey);
    }
}
