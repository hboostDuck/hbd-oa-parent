package com.orbit.auth.activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProcessTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Test
    public void SingleSuspendProcessInstance() {
        String processInstanceId = "8bdff984-ab53-11ed-9b17-f8e43b734677";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //获取到当前流程定义是否为暂停状态   suspended方法为true代表为暂停   false就是运行的
        boolean suspended = processInstance.isSuspended();
        if (suspended) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例:" + processInstanceId + "激活");
        } else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例:" + processInstanceId + "挂起");
        }
    }
    @Test
    public void suspendProcessInstance() {
        ProcessDefinition qingjia = repositoryService.createProcessDefinitionQuery().processDefinitionKey("qingjia").singleResult();
        // 获取到当前流程定义是否为暂停状态 suspended方法为true是暂停的，suspended方法为false是运行的
        boolean suspended = qingjia.isSuspended();
        if (suspended) {
            // 暂定,那就可以激活
            // 参数1:流程定义的id  参数2:是否激活    参数3:时间点
            repositoryService.activateProcessDefinitionById(qingjia.getId(), true, null);
            System.out.println("流程定义:" + qingjia.getId() + "激活");
        } else {
            repositoryService.suspendProcessDefinitionById(qingjia.getId(), true, null);
            System.out.println("流程定义:" + qingjia.getId() + "挂起");
        }
    }


    //创建流程实例，制定BusinessKey
    @Test
    public void startProcessInstance() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia", "1001");
//        System.out.println(processInstance.getId());
//        System.out.println(processInstance.getName());
//        System.out.println(processInstance.getProcessDefinitionId());
//        System.out.println(processInstance.getActivityId());
        System.out.println(processInstance.getBusinessKey());
    }

    //单个文件部署
    @Test
    public void deployProcess() {
        Deployment deploy = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("process/qingjia.bpmn20.xml")
                .addClasspathResource("process/qingjia.png")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void startProcess() {
        ProcessInstance p = runtimeService.startProcessInstanceByKey("qingjia");
        System.out.println("流程实例"+p.getId());
        System.out.println("流程定义"+p.getProcessDefinitionId());
        System.out.println("流程活动"+p.getActivityId());
    }

    @Test
    public void findTaskList() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
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
        Task task = taskService.createTaskQuery().taskAssignee("zhangsan").singleResult();
        taskService.complete(task.getId());

    }

    @Test
    public void findHistoryTaskList() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee("lisi").finished().list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("任务ID："+historicTaskInstance.getId());
            System.out.println("任务名称："+historicTaskInstance.getName());
            System.out.println("任务创建时间："+historicTaskInstance.getCreateTime());
            System.out.println("任务办理人："+historicTaskInstance.getAssignee());
            System.out.println("流程实例ID："+historicTaskInstance.getProcessInstanceId());
        }
    }
}
