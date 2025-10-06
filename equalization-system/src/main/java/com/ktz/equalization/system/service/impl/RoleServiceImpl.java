package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Role;
import com.ktz.equalization.mapper.RoleMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:03
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleMapper> implements RoleService {

    private List<Role> temp = new ArrayList<Role>();

    @Override
    public BaseResult insertOne(Role entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("新增角色成功", entity);
    }

    @Override
    public List<Role> list(Role entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Role> list(Integer page, Integer size, Role entity) {
        PageHelper.startPage(page, size);
        List<Role> roles = mapper.list(entity);
        return new PageInfo<>(roles);
    }

    @Override
    public List<TreeResult> listTree() {
        List<Role> roles = mapper.listAll();
        for (Role role : roles) {
            convertRole(roles, role);
        }
        roles.removeAll(temp);
        temp = new ArrayList<Role>();
        return convert(roles);
    }

    @Override
    public Role getParent(Long parentId) {
        return mapper.getParent(parentId);
    }

    private List<TreeResult> convert(List<Role> roles) {
        List<TreeResult> treeResults = new ArrayList<TreeResult>();
        for (Role role : roles) {
            TreeResult treeResult = convertRoleToTree(role);
            treeResults.add(treeResult);
        }
        return treeResults;
    }

    private TreeResult convertRoleToTree(Role role) {
        TreeResult treeResult = new TreeResult();
        treeResult.setId(role.getId());
        treeResult.setTitle(role.getName());
        List<TreeResult> children = new ArrayList<TreeResult>();
        for (Role r : role.getChildRole()) {
            children.add(convertRoleToTree(r));
        }
        treeResult.setChildren(children);
        return treeResult;
    }

    private void convertRole(List<Role> roles, Role role) {
        List<Role> childRole = getChildRole(roles, role);
        role.setChildRole(childRole);
        for (Role r : childRole) {
            convertRole(roles, r);
        }
    }

    private List<Role> getChildRole(List<Role> roles, Role role) {
        List<Role> childRole = new ArrayList<Role>();
        for (Role r : roles) {
            if (r.getParentId() == null) {
                continue;
            }
            if (r.getParentId().longValue() == role.getId().longValue()) {
                childRole.add(r);
                temp.add(r);
            }
        }
        return childRole;
    }
}
