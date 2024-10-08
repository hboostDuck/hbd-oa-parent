package com.orbit.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orbit.model.process.ProcessTemplate;
import com.orbit.model.process.ProcessType;
import com.orbit.process.mapper.ProcessTypeMapper;
import com.orbit.process.service.ProcessTemplateService;
import com.orbit.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批类型(ProcessType)表服务实现类
 *
 * @author makejava
 * @since 2024-10-07 18:31:16
 */
@Service("processTypeService")
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {
    @Autowired
    private ProcessTemplateService processTemplateService;

    @Override
    public List<ProcessType> findProcessType() {
        //1 查询所有审批分类，返回list集合
        List<ProcessType> processTypeList = baseMapper.selectList(null);

        //2 遍历返回所有审批分类list集合
        for (ProcessType processType:processTypeList) {
            //3 得到每个审批分类，根据审批分类id查询对应审批模板
            //审批分类id
            Long typeId = processType.getId();
            //根据审批分类id查询对应审批模板
            LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessTemplate::getProcessTypeId,typeId);
            List<ProcessTemplate> processTemplateList = processTemplateService.list(wrapper);

            //4 根据审批分类id查询对应审批模板数据（List）封装到每个审批分类对象里面
            processType.setProcessTemplateList(processTemplateList);
        }
        return processTypeList;
    }
}

