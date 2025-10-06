package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import com.ktz.equalization.commons.util.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author 刘世阳
 * @date 2021/1/25 8:56
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    private long userId;
    private long roleId;
}