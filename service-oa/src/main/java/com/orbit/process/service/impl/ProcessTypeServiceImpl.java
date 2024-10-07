package com.orbit.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orbit.model.process.ProcessType;
import com.orbit.process.mapper.ProcessTypeMapper;
import com.orbit.process.service.ProcessTypeService;
import org.springframework.stereotype.Service;

/**
 * 审批类型(ProcessType)表服务实现类
 *
 * @author makejava
 * @since 2024-10-07 18:31:16
 */
@Service("processTypeService")
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {

}

