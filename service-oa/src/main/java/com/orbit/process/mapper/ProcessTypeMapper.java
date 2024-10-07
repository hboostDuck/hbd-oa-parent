package com.orbit.process.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orbit.model.process.ProcessType;
import org.apache.ibatis.annotations.Mapper;


/**
 * 审批类型(ProcessType)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-07 18:31:16
 */
@Mapper
public interface ProcessTypeMapper extends BaseMapper<ProcessType> {

}

