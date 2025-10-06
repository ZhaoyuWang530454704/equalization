package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午3:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity {

    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    
    @NotNull(message = "权限ID不能为空")
    private Long permissionId;

}
