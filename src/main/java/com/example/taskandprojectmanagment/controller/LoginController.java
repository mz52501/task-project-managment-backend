package com.example.taskandprojectmanagment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loadForm() {
        return "home";
    }

    @RequestMapping(value = "/bla", method = RequestMethod.GET)
    public String loadForm2() {
        return "home";
    }

    @RequestMapping(value = "/blabla", method = RequestMethod.GET)
    public String load() {
        return "home";
    }
}
