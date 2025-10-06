package com.ktz.equalization.web.controller;

import com.ktz.equalization.domain.Permission;
import com.ktz.equalization.domain.User;
import com.ktz.equalization.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/7 下午12:36
 */
@Controller
public class IndexController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Permission> permissions = permissionService.list(user.getId());
        model.addAttribute("permissions", permissions);
        return "index";
    }

}
