package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class EmployeeController {

    @GetMapping("/getEmployee")
    public String getEmployee(WebRequest webReq) {
        return "employee";
    }

}
