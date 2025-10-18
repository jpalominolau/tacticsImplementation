package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/user/home")
    public String userHome() {
        return "<html><body>"
            + "<h3>Welcome User!</h3>"
            + "<form action='/logout' method='post'>"
            + "<input type='submit' value='Logout'/>"
            + "</form>"
            + "</body></html>";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "<html><body>"
            + "<h3>Welcome Admin!</h3>"
            + "<form action='/logout' method='post'>"
            + "<input type='submit' value='Logout'/>"
            + "</form>"
            + "</body></html>";
    }
}
