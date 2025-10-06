package com.ktz.equalization.web.controller;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.LayUiResult;
import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.domain.Role;
import com.ktz.equalization.domain.RolePermission;
import com.ktz.equalization.system.service.PermissionService;
import com.ktz.equalization.system.service.RolePermissionService;
import com.ktz.equalization.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/14 18:07
 * @Description:
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/policy-user")
    public String content() {
        return "policy-user";
    }

    @GetMapping("")
    public String role() {
        return "role";
    }

    @GetMapping("/view/{id}")
    public String roleUpdate(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getOne(id);
        Role parentRole = roleService.getParent(role.getParentId());
        model.addAttribute("role", role);
        model.addAttribute("parentRole", parentRole);
        return "roleUpdate";
    }

    @GetMapping("/add")
    public String roleAdd() {
        return "roleAdd";
    }

    @PostMapping("/insert")
    @ResponseBody
    public BaseResult insert(Role role) {
        return roleService.insertOne(role);
    }

    @GetMapping("/list")
    @ResponseBody
    public LayUiResult<Role> list() {
        List<Role> roles = roleService.listAll();
        return LayUiResult.createResult(0, "获取角色数据", roles.size(), roles);
    }

    @GetMapping("/listTree")
    @ResponseBody
    public List<TreeResult> listTree() {
        return roleService.listTree();
    }

    @PutMapping("/update")
    @ResponseBody
    public BaseResult update(@RequestBody Role role) {
        roleService.update(role);
        return BaseResult.success("修改角色成功");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable("id") Long id) {
        roleService.deleteOne(id);
        return BaseResult.success("删除角色成功");
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public BaseResult deleteMulti(@RequestBody String[] ids) {
        roleService.deleteMulti(ids);
        return BaseResult.success("删除角色成功");
    }

}
