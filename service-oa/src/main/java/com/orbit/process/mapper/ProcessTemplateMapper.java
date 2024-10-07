package com.orbit.process.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orbit.model.process.ProcessTemplate;
import org.apache.ibatis.annotations.Mapper;


/**
 * 审批模板(ProcessTemplate)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-07 18:31:15
 */
@Mapper
public interface ProcessTemplateMapper extends BaseMapper<ProcessTemplate> {

}

