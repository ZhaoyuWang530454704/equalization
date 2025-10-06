package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * @author 曾泉明
 * @date 2021/1/6 下午1:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Field extends BaseEntity {

    @Length(min = 1, message = "领域名称不能为空")
    private String name;
}
