package com.ktz.equalization.system.service.impl;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.domain.UserRole;
import com.ktz.equalization.mapper.UserRoleMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘世阳
 * @date 2021/1/25 9:20
 * @description:
 */
@Service
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {
    @Override
    public BaseResult insertOne(UserRole entity) {
        mapper.insertOne(entity);
        return BaseResult.success();
    }

    @Override
    public List<UserRole> list(UserRole entity) {
        return null;
    }

    @Override
    public PageInfo<UserRole> list(Integer page, Integer size, UserRole entity) {
        return null;
    }
}
