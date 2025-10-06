package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.User;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:00
 */
public interface UserService extends BaseService<User> {

    User login(String email, String password);
}
