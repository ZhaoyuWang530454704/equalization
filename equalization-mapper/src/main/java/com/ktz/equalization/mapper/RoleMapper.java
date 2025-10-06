package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午7:17
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Role getParent(@Param("parentId") Long parentId);
}
