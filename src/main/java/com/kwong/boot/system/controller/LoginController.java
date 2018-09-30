package com.kwong.boot.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kwong.boot.core.shiro.ShiroKit;

/** 
* @Description: 登陆控制器
* @author: kwong
* @date: Sep 30, 2018
*/
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    	System.err.println(111);
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return "redirect:/";
        } else {
            return "/login.html";
        }
    }
}
