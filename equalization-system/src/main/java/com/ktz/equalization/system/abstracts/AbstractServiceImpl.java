package com.ktz.equalization.system.abstracts;

import com.ktz.equalization.commons.persistence.BaseEntity;
import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午7:58
 */

public abstract class AbstractServiceImpl<T extends BaseEntity, M extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    protected M mapper;
    @Override
    public void deleteOne(Long id) {
        mapper.deleteOne(id);
    }

    @Override
    public void deleteMulti(String[] ids) {
        mapper.deleteMulti(ids);
    }

    @Override
    public void update(T entity) {
        entity.setUpdateTime(LocalDateTime.now());
        mapper.update(entity);
    }

    @Override
    public T getOne(Long id) {
        return mapper.getOne(id);
    }

    @Override
    public List<T> listAll() {
        return mapper.listAll();
    }
}
