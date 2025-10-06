package com.ktz.equalization.web.controller;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.LayUiResult;
import com.ktz.equalization.domain.User;
import com.ktz.equalization.domain.UserRole;
import com.ktz.equalization.system.service.RoleService;
import com.ktz.equalization.system.service.UserRoleService;
import com.ktz.equalization.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:18
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @GetMapping("/personal")
    public String personal() {
        return "personal";
    }

    @GetMapping("/view")
    public String checkUser() {
        return "checkUser";
    }
    @GetMapping("/userEdit")
    public String userEdit() {
        return "userEdit";
    }

    @GetMapping("/add")
    public String addUser() {
        return "add";
    }
    @GetMapping("/listAll")
    @ResponseBody
    public LayUiResult<User> listAll(){
        List<User> users = userService.listAll();
        return LayUiResult.createResult(0,"ok",users.size(),users);
    }
    @DeleteMapping("/deleteMulti")
    public void deleteMulti(@RequestBody String ids[]){
        userService.deleteMulti(ids);
    }
    @DeleteMapping("/deleteOne")
    public BaseResult deleteOne(Long id){
        userService.deleteOne(id);
        return BaseResult.success();
    }
    @GetMapping("/getOne")
    public void getOne(@RequestBody Long id){
        userService.deleteOne(id);
    }
    @PostMapping("/insertOne")
    @ResponseBody
    public BaseResult insertOne(@RequestBody User user){
        userService.insertOne(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(user.getRoleId());
        userRole.setCreateTime(LocalDateTime.now());
        userRole.setUpdateTime(LocalDateTime.now());
        userRoleService.insertOne(userRole);
        return BaseResult.success();
    }
    @PostMapping("/update")
    @ResponseBody
    public BaseResult update(@RequestBody User user){
        userService.update(user);
        return BaseResult.success();
    }
    @GetMapping("/viewEdit/{id}")
    public String roleUpdate(@PathVariable("id") Long id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute(user);
        return "userEdit";
    }
}
