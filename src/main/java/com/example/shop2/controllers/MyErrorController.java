package com.example.shop2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
