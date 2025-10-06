package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.Permission;
import com.ktz.equalization.mapper.PermissionMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.PermissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:02
 */
@Service
public class PermissionServiceImpl extends AbstractServiceImpl<Permission, PermissionMapper> implements PermissionService {

    private List<Permission> temp = new ArrayList<Permission>();

    @Override
    public BaseResult insertOne(Permission entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        mapper.insertOne(entity);
        return BaseResult.success("新增权限成功");
    }

    @Override
    public List<Permission> list(Permission entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<Permission> list(Integer page, Integer size, Permission entity) {
        PageHelper.startPage(page, size);
        List<Permission> permissions = mapper.list(entity);
        return new PageInfo<>(permissions);
    }

    @Override
    public List<Permission> list(Long userId) {
        List<Permission> rootPermissions = mapper.listByUserId(userId);
        for (Permission rootPermission : rootPermissions) {
            convertPermission(rootPermissions, rootPermission);
        }
        rootPermissions.removeAll(temp);
        temp = new ArrayList<Permission>();
        return rootPermissions;
    }

    @Override
    public List<TreeResult> listTree() {
        List<Permission> rootPermissions = mapper.listAll();
        for (Permission rootPermission : rootPermissions) {
            convertPermission(rootPermissions, rootPermission);
        }
        rootPermissions.removeAll(temp);
        temp = new ArrayList<Permission>();
        return convert(rootPermissions);
    }

    @Override
    public List<TreeResult> listTree(Long roleId) {
        List<Permission> allPermissions = mapper.listAll();
        List<Long> permissionIds = mapper.listIdByRoleId(roleId);
        for (Permission permission : allPermissions) {
            convertPermission(allPermissions, permission);
        }
        allPermissions.removeAll(temp);
        List<TreeResult> treeResults = convert(allPermissions);
        for (TreeResult treeResult : treeResults) {
            treeChecked(treeResult, permissionIds);
        }
        return treeResults;
    }

    private void treeChecked(TreeResult treeResult, List<Long> permissionIds) {
        for (Long permissionId : permissionIds) {
            treeChecked(treeResult, permissionId);
        }
    }

    private boolean treeChecked(TreeResult treeResult, Long permissionId) {
        if (treeResult.getId().longValue() == permissionId) {
            treeResult.setChecked(true);
            return true;
        }
        for (TreeResult child : treeResult.getChildren()) {
            boolean flag = treeChecked(child, permissionId);
            if (flag) {
                break;
            }
        }
        return false;
    }

    private List<TreeResult> convert(List<Permission> permissions) {
        List<TreeResult> treeResults = new ArrayList<TreeResult>();
        for (Permission permission : permissions) {
            treeResults.add(convertToTree(permission));
        }
        return treeResults;
    }

    private TreeResult convertToTree(Permission permission) {
        TreeResult treeResult = new TreeResult();
        treeResult.setId(permission.getId());
        treeResult.setTitle(permission.getName());
        List<TreeResult> children = new ArrayList<TreeResult>();
        for (Permission p : permission.getChildPermission()) {
            children.add(convertToTree(p));
        }
        treeResult.setChildren(children);
        return treeResult;
    }

    /**
     * 将当前权限列表调整为权限树
     *
     * @param permissions 权限列表
     * @param permission  父权限
     */
    private void convertPermission(List<Permission> permissions, Permission permission) {
        List<Permission> childPermissions = getChildPermission(permissions, permission);
        permission.setChildPermission(childPermissions);
        for (Permission childPermission : childPermissions) {
            convertPermission(permissions, childPermission);
        }
    }



    /**
     * 获取子权限列表
     *
     * @param permissions 权限列表
     * @param permission  父权限
     * @return 指定父权限的子权限列表
     */
    private List<Permission> getChildPermission(List<Permission> permissions, Permission permission) {
        List<Permission> childPermissions = new ArrayList<Permission>();
        for (Permission p : permissions) {
            if (p.getParentId() == null) {
                continue;
            }
            if (p.getParentId().longValue() == permission.getId().longValue()) {
                childPermissions.add(p);
                temp.add(p);
            }
        }
        return childPermissions;
    }

}
