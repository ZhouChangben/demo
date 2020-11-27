package com.zcb.demo.controller;

import com.zcb.demo.mapper.UserMapper;
import com.zcb.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserMapper usermapper;

    @PostMapping("/login")
    public String doLogin(@RequestParam("username")String username,
                          @RequestParam("password")String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
        User user = new User();
        user = usermapper.signin(username,password);
        if (user != null){
            response.addCookie(new Cookie("name",user.getName()));
            return "index";
        }
        else{
            return "login";
        }

    }

    @PostMapping("/register")
    public String doRegister(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             Model model){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        if (user != null){
            usermapper.insert(user);
            return "index";
        }
        else {
            return "register";
        }

    }
}
