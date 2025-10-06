package com.ktz.equalization.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktz.equalization.commons.persistence.BaseEntity;
import com.ktz.equalization.commons.util.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午5:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Length(min = 6, max = 20, message = "用户名长度必须介于 6 - 20 位之间")
    private String username;

    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱地址格式不正确")
    private String email;
    private long roleId;
}
