package com.ktz.equalization.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 刘世阳
 * @date 2021/1/25 15:59
 * @description:
 */
@Controller
public class IndexUserController {
    @GetMapping("/indexContent-user")
    public String indexContextUser(){return "indexContent-user";}
    @GetMapping("/policy-user")
    public String policyUser(){return "policy-user";}
    @GetMapping("/economy-user")
    public String economyUser(){return "economy-user";}
    @GetMapping("/index-user")
    public String indexUser(){return "index-user";}
}
