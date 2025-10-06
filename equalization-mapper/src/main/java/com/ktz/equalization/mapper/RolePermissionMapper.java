package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午3:40
 */
@Mapper
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void insertMulti(List<RolePermission> rolePermissions);
}
