package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Dialogue;
import com.ktz.equalization.domain.Region;
import com.ktz.equalization.mapper.RegionMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.RegionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:37
 * @Description:
 */
@Service
public class RegionServiceImpl extends AbstractServiceImpl<Region, RegionMapper>implements RegionService {
    @Override
    public BaseResult insertOne(Region entity) {
        String validator = BeanValidator.validator(entity);
        if (validator !=null){
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("地区添加成功");
    }

    @Override
    public List<Region> list(Region entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Region> list(Integer page, Integer size, Region entity) {
        PageHelper.startPage(page, size);
        List<Region> regions = mapper.list(entity);
        return new PageInfo<>(regions);
    }
}
