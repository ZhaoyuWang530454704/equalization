package com.ktz.equalization.web.controller;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.LayUiResult;
import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.domain.Permission;
import com.ktz.equalization.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/7 下午1:58
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    public String permission() {
        return "power";
    }

    @GetMapping("/list")
    @ResponseBody
    public LayUiResult<Permission> list() {
        List<Permission> permissions = permissionService.listAll();
        return LayUiResult.createResult(0, "获取权限数据", permissions.size(), permissions);
    }

    @GetMapping("/listTree")
    @ResponseBody
    public List<TreeResult> listTree(@RequestParam("id") Long id) {
        if (id != null) {
            return permissionService.listTree(id);
        }
        return permissionService.listTree();
    }
    @DeleteMapping("/deleteOne")
    public void deleteOne(@RequestBody Long id){
        permissionService.deleteOne(id);
    }
    @PostMapping("/update")
    @ResponseBody
    public BaseResult update(@RequestBody Permission permission){
        permissionService.update(permission);
        return BaseResult.success();
    }
    @DeleteMapping("/deleteMulti")
    public void deleteMulti(@RequestBody String ids[]){
        permissionService.deleteMulti(ids);
    }
}
