package com.innopo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class testContr {
    @RequestMapping(value = "/main", method = {RequestMethod.POST, RequestMethod.GET})
    public String showMain(){
        int a = 0;
//        SecurityContextHolder.getContext().getAuthentication()
        return "main";
    }
}
