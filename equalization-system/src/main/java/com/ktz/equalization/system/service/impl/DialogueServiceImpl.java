package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Dialogue;
import com.ktz.equalization.domain.Permission;
import com.ktz.equalization.mapper.DialogueMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.DialogueService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:08
 * @Description:
 */
@Service
public class DialogueServiceImpl extends AbstractServiceImpl<Dialogue, DialogueMapper> implements DialogueService {

    @Override
    public BaseResult insertOne(Dialogue entity) {
        String validator = BeanValidator.validator(entity);
        if (validator !=null){
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("问题添加成功");
    }

    @Override
    public List<Dialogue> list(Dialogue entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Dialogue> list(Integer page, Integer size, Dialogue entity) {
        PageHelper.startPage(page, size);
        List<Dialogue> dialogues = mapper.list(entity);
        return new PageInfo<>(dialogues);
    }

}
