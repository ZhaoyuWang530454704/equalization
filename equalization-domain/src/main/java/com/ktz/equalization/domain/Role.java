package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午6:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private Long parentId;

    @Length(min = 1, message = "角色名称不能为空")
    private String name;

    private List<Role> childRole;
}
