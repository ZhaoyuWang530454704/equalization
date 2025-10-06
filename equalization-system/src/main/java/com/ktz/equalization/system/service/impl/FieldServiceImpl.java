package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Field;
import com.ktz.equalization.domain.Policy;
import com.ktz.equalization.mapper.FieldMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.FieldService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:49
 * @Description:
 */
@Service
public class FieldServiceImpl extends AbstractServiceImpl<Field, FieldMapper> implements FieldService {
    @Override
    public BaseResult insertOne(Field entity) {
        String validator = BeanValidator.validator(entity);
        if (validator !=null){
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("均等化区域添加成功");
    }

    @Override
    public List<Field> list(Field entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Field> list(Integer page, Integer size, Field entity) {
        PageHelper.startPage(page, size);
        List<Field> fields = mapper.list(entity);
        return new PageInfo<>(fields);
    }
}
