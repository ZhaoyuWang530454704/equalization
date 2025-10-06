package com.ktz.equalization.web.controller;

import com.ktz.equalization.domain.User;
import com.ktz.equalization.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author 曾泉明
 * @date 2021/1/7 下午12:22
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        User user = userService.login(email, password);
        if (user != null) {
            if(user.getId()==1) {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
            else{
                return "redirect:/index-user";
            }
        }
        model.addAttribute("msg", "邮箱或密码错误");
        return "redirect:/";
    }

}
