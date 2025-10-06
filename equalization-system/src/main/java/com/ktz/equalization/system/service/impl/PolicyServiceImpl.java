package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Policy;
import com.ktz.equalization.mapper.PolicyMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.PolicyService;
import com.ktz.equalization.system.task.PolicyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:43
 * @Description:
 */
@Service
public class PolicyServiceImpl extends AbstractServiceImpl<Policy, PolicyMapper> implements PolicyService {

    @Autowired
    private PolicyTask policyTask;

    @Override
    public BaseResult insertOne(Policy entity) {
        String validator = BeanValidator.validator(entity);
        if (validator !=null){
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("政策添加成功");
    }

    @Override
    public List<Policy> list(Policy entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Policy> list(Integer page, Integer size, Policy entity) {
        PageHelper.startPage(page, size);
        List<Policy> policies = mapper.list(entity);
        return new PageInfo<>(policies);
    }

    @Override
    public void save(String key) {
        List<Policy> policies = policyTask.parse2policy(key);
        mapper.insertMulti(policies);
    }
}
