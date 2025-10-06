package com.ktz.equalization.web.controller;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.domain.RolePermission;
import com.ktz.equalization.system.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/15 下午4:25
 */
@Controller
@RestController
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/rolePermissions")
    public BaseResult insertMulti(@RequestBody List<RolePermission> rolePermissions) {
        return rolePermissionService.insertMulti(rolePermissions);
    }

}
