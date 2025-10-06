package com.ktz.equalization.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.validator.BeanValidator;
import com.ktz.equalization.domain.User;
import com.ktz.equalization.mapper.UserMapper;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;
import com.ktz.equalization.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:01
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserMapper> implements UserService {
    @Override
    public BaseResult insertOne(User entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        // 密码使用 MD5 加密
        entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
        mapper.insertOne(entity);

        return BaseResult.success("新增用户成功");
    }

    @Override
    public List<User> list(User entity) {
        return mapper.list(entity);
    }

    @Override
    public PageInfo<User> list(Integer page, Integer size, User entity) {
        PageHelper.startPage(page, size);
        List<User> users = mapper.list(entity);
        return new PageInfo<>(users);
    }

    @Override
    public User login(String email, String password) {
        User user = null;
        try {
            user = mapper.getByEmailAndPwd(email, DigestUtils.md5DigestAsHex(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        System.out.println( DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}
