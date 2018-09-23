package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
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
    public String loginForm(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        modelMap.put("auth_failed", error != null);
        return "login";
    }

    @PostMapping(path = "/auth/request_code")
    public ResponseEntity<?> requestCode(@RequestParam("phone") String phone) {
        otpService.sendCode(phone);
        return ResponseEntity.ok().build();
    }

}
