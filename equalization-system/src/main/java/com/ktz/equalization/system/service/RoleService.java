package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.Role;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:02
 */
public interface RoleService extends BaseService<Role> {

    List<TreeResult> listTree();

    Role getParent(Long parentId);
}
