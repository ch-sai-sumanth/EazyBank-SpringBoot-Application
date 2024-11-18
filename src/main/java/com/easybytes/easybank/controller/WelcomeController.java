package com.easybytes.easybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to EazyBank";
    }


    @GetMapping("/myBalance")
    public String myBalance() {
        return "My balance endpoint";
    }

    @GetMapping("/myLoans")
    public String myLoans() {
        return "My loans endpoint";
    }

    @GetMapping("/myCards")
    public String myCards() {
        return "My cards endpoint";
    }

    @GetMapping("/myAccount")
    public String myAccount() {
        return "My account endpoint";
    }

    @GetMapping("/notices")
    public String notices() {
        return "Notices endpoint";
    }

    @GetMapping("/contact")
    public String contact() {
        return "Contact endpoint";
    }

}