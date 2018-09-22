package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:30 PM
 */
@Controller
public class AuthController {

    private final OtpService otpService;

    @Autowired
    public AuthController(OtpService otpService) {
        this.otpService = otpService;
    }

    @GetMapping(path = {"/login", "/auth/login"})
    public String loginForm() {
        return "login";
    }

    @PostMapping(path = "/auth/request_code")
    public ResponseEntity<?> requestCode(@RequestParam("phone") String phone) {
        otpService.sendCode(phone);
        return ResponseEntity.ok().build();
    }

}
