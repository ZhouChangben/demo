package com.zcb.demo.controller;

import com.zcb.demo.mapper.UserMapper;
import com.zcb.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper usermapper;



    @GetMapping("/")
    public String index(HttpServletRequest request) {
        /*System.out.println("hhh");*/
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) {
                String name = cookie.getValue();
                User user = usermapper.findByName(name);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                System.out.println("找到了cookie");
                break;
            }
        }
        System.out.println("没有找到cookies");
        return "index";
    }

    @GetMapping("/register")
    public String Signin() {
        return "register";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

}
