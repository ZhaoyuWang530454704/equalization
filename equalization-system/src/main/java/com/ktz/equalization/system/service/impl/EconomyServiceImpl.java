package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.domain.Field;
import com.ktz.equalization.domain.Policy;
import com.ktz.equalization.domain.Region;
import com.ktz.equalization.mapper.EconomyMapper;
import com.ktz.equalization.mapper.FieldMapper;
import com.ktz.equalization.mapper.RegionMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.EconomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:46
 * @Description:
 */
@Service
public class EconomyServiceImpl extends AbstractServiceImpl<Economy, EconomyMapper> implements EconomyService {

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public BaseResult insertOne(Economy entity) {
        String validator = BeanValidator.validator(entity);
        if (validator !=null){
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("经济政策添加成功");
    }

    @Override
    public List<Economy> listAll() {
        List<Economy> economies = mapper.listAll();
        for (Economy economy : economies) {
            Region region = regionMapper.getOne(economy.getRegionId());
            Field field = fieldMapper.getOne(economy.getFieldId());
            economy.setRegion(region);
            economy.setField(field);
        }
        return economies;
    }

    @Override
    public List<Economy> list(Economy entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Economy> list(Integer page, Integer size, Economy entity) {
        PageHelper.startPage(page, size);
        List<Economy> economies = mapper.list(entity);
        for (Economy economy : economies) {
            Region region = regionMapper.getOne(economy.getRegionId());
            Field field = fieldMapper.getOne(economy.getFieldId());
            economy.setRegion(region);
            economy.setField(field);
        }
        return new PageInfo<>(economies);
    }

    @Override
    public void insertMulti(List<Economy> economies) {
        mapper.insertMulti(economies);
    }
}
