package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午7:16
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户 id 获取权限
     * @param userId 用户 id
     * @return 权限对象组成的 List 集合
     */
    List<Permission> listByUserId(@Param("userId") Long userId);

    List<Permission> listByRoleId(@Param("roleId") Long roleId);

    List<Long> listIdByRoleId(@Param("roleId") Long roleId);
}
