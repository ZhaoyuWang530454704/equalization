package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.Permission;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:02
 */
public interface PermissionService extends BaseService<Permission> {

    /**
     * 根据用户 id 获取权限
     * @param userId 用户 id
     * @return 权限对象组成的 List 集合
     */
    List<Permission> list(Long userId);

    List<TreeResult> listTree();

    List<TreeResult> listTree(Long roleId);
}
