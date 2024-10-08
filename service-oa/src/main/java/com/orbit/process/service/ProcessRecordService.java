package com.orbit.process.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.process.ProcessRecord;


/**
 * 审批记录(ProcessRecord)表服务接口
 *
 * @author makejava
 * @since 2024-10-07 22:17:25
 */
public interface ProcessRecordService extends IService<ProcessRecord> {
    void record(Long processId, Integer status, String description);
}

