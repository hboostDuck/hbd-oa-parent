package com.orbit.process.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.process.ProcessType;

import java.util.List;


/**
 * 审批类型(ProcessType)表服务接口
 *
 * @author makejava
 * @since 2024-10-07 18:31:16
 */
public interface ProcessTypeService extends IService<ProcessType> {
    List<ProcessType> findProcessType();
}

