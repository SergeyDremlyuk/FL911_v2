package com.innopo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String showIndex(){
        return "index";
    }

    @RequestMapping(value = "/loginSuccessful", method = {RequestMethod.POST})
    public String showMain(){
        return "redirect:/main";
    }

}
