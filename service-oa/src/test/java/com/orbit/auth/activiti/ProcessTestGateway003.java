package com.orbit.auth.activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class ProcessTestGateway003 {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;


    //单个文件部署
    @Test
    public void deployProcess() {
        Deployment deploy = repositoryService.createDeployment()
                .name("请假流程003")
                .addClasspathResource("process/qingjia003.bpmn20.xml")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());



    }

    @Test
    public void startProcess() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("day", 2);

        ProcessInstance p = runtimeService.startProcessInstanceByKey("qingjia003");
        System.out.println("流程实例" + p.getId());
        System.out.println("流程定义" + p.getProcessDefinitionId());
    }

    @Test
    public void findTaskList() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("xiaocui").list();
        for (Task task : tasks) {
            System.out.println("任务ID："+task.getId());
            System.out.println("任务名称："+task.getName());
            System.out.println("任务创建时间："+task.getCreateTime());
            System.out.println("任务办理人："+task.getAssignee());
            System.out.println("流程实例ID："+task.getProcessInstanceId());
        }
    }

    @Test
    public void completeTask() {
        Task task = taskService.createTaskQuery().taskAssignee("xiaocui").singleResult();
        taskService.complete(task.getId());

    }


}
