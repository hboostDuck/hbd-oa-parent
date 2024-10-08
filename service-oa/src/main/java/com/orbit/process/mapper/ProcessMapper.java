package com.orbit.process.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orbit.model.process.Process;
import com.orbit.vo.process.ProcessQueryVo;
import com.orbit.vo.process.ProcessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 审批类型(Process)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-07 19:13:41
 */
@Mapper
public interface ProcessMapper extends BaseMapper<Process> {



    IPage<ProcessVo> selectPage(@Param("page") Page<ProcessVo> page, @Param("vo") ProcessQueryVo processQueryVo);
}

