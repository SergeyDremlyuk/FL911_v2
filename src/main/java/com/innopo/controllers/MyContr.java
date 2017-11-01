package com.innopo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyContr {

   @RequestMapping(value = "/chat", method = {RequestMethod.POST, RequestMethod.GET})
 public String showChat(){
        return "chat";
   }
}
