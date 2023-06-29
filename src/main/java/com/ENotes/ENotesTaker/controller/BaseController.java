package com.ENotes.ENotesTaker.controller;

import com.ENotes.ENotesTaker.entity.UserInfo;
import com.ENotes.ENotesTaker.service.EmailServiceImpl;
import com.ENotes.ENotesTaker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @GetMapping(value = {"/", "/home"})
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/register-user")
    public String saveUser(@ModelAttribute UserInfo user, Model model, HttpSession session){
        //System.out.println(user);
        UserInfo userInfo = userService.registerUser(user);

        if(userInfo!=null){
            // sendMail Method
            String res = sendMail(user);
            session.setAttribute("msg","User Registered Successfully "+res);
        }
        else{
            session.setAttribute("msg","Something wrong on server");
        }
        return "redirect:/signup";
    }
    private String sendMail(UserInfo userInfo){
        return emailServiceImpl.sendMail(userInfo);
    }
}
