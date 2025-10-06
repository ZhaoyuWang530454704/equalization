package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.RolePermission;
import com.ktz.equalization.mapper.RolePermissionMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午3:52
 */
@Service
public class RolePermissionServiceImpl extends AbstractServiceImpl<RolePermission, RolePermissionMapper> implements RolePermissionService {
    @Override
    public BaseResult insertOne(RolePermission entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("新增成功");
    }

    @Override
    public List<RolePermission> list(RolePermission entity) {
        return null;
    }

    @Override
    public PageInfo<RolePermission> list(Integer page, Integer size, RolePermission entity) {
        return null;
    }

    @Override
    public BaseResult insertMulti(List<RolePermission> rolePermissions) {
        try {
            for (RolePermission rolePermission : rolePermissions) {
                rolePermission.setCreateTime(LocalDateTime.now());
                rolePermission.setUpdateTime(LocalDateTime.now());
            }
            mapper.insertMulti(rolePermissions);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("新增失败");
        }
        return BaseResult.success("新增成功");
    }
}
