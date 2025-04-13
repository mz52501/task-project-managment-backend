package com.example.taskandprojectmanagment.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginController {

    @GetMapping(value = "/home")
    public String loadForm() {
        return "home";
    }

    @GetMapping(value = "/bla")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String loadForm2() {
        return "home";
    }

    @GetMapping(value = "/blabla" )
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public String load() {
        return "home";
    }
}
