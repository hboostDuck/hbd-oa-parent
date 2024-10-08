package com.orbit.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.process.Process;
import com.orbit.model.process.ProcessType;
import com.orbit.vo.process.ApprovalVo;
import com.orbit.vo.process.ProcessFormVo;
import com.orbit.vo.process.ProcessQueryVo;
import com.orbit.vo.process.ProcessVo;

import java.util.List;
import java.util.Map;

/**
 * 审批类型(Process)表服务接口
 *
 * @author makejava
 * @since 2024-10-07 19:13:42
 */
public interface ProcessService extends IService<Process> {

    IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo);

    void deployByZip(String deployPath);


    void startUp(ProcessFormVo processFormVo);

    IPage<ProcessVo> findPending(Page<Process> pageParam);

    Map<String, Object> show(Long id);

    void approve(ApprovalVo approvalVo);

    IPage<ProcessVo> findProcessed(Page<Process> pageParam);

    IPage<ProcessVo> findStarted(Page<ProcessVo> pageParam);
}

