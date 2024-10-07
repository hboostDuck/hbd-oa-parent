package com.orbit.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.process.ProcessTemplate;


/**
 * 审批模板(ProcessTemplate)表服务接口
 *
 * @author makejava
 * @since 2024-10-07 18:31:16
 */
public interface ProcessTemplateService extends IService<ProcessTemplate> {
    IPage<ProcessTemplate> selectPage(Page<ProcessTemplate> pageParam);

    void publish(Long id);
}

