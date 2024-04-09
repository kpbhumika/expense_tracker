package com.expense_tracker.expense_tracker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController

public class firstClass {
    @RequestMapping("/welcome")
    public String hello(){
        return "Welcome to expense tracker app";
    }


}
