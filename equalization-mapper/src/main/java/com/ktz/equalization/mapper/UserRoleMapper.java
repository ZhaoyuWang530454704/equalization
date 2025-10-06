package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 刘世阳
 * @date 2021/1/25 8:58
 * @description:
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
