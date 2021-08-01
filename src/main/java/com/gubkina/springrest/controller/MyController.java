package com.gubkina.springrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MyController {

    @GetMapping(value = "/admin")
    public String getAdminPage() {
        return "adminPage";
    }


    @GetMapping(value = "/user")
    public String getUserPage() {
        return "userPage";
    }
}