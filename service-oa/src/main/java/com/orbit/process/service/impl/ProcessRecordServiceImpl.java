package com.orbit.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.orbit.auth.service.SysUserService;
import com.orbit.model.process.ProcessRecord;
import com.orbit.model.system.SysUser;
import com.orbit.process.mapper.ProcessRecordMapper;
import com.orbit.process.service.ProcessRecordService;
import com.orbit.security.custom.LoginUserInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审批记录(ProcessRecord)表服务实现类
 *
 * @author makejava
 * @since 2024-10-07 22:17:25
 */
@Service("processRecordService")
public class ProcessRecordServiceImpl extends ServiceImpl<ProcessRecordMapper, ProcessRecord> implements ProcessRecordService {


    @Autowired
    private SysUserService sysUserService;

    @Override
    public void record(Long processId, Integer status, String description) {
        SysUser sysUser = sysUserService.getById(LoginUserInfoHelper.getUserId());
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setProcessId(processId);
        processRecord.setStatus(status);
        processRecord.setDescription(description);
        processRecord.setOperateUserId(sysUser.getId());
        processRecord.setOperateUser(sysUser.getName());
        baseMapper.insert(processRecord);
    }
}

