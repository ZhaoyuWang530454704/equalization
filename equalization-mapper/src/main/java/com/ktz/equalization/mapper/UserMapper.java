package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午7:12
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getByEmailAndPwd(@Param("email") String email, @Param("password") String password);
}
