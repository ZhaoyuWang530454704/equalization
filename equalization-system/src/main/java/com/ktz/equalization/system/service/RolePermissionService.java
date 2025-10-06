package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.RolePermission;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午3:52
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    BaseResult insertMulti(List<RolePermission> rolePermissions);
}
