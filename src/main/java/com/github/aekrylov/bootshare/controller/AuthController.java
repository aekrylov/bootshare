package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.service.OtpService;
import com.github.aekrylov.bootshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private final UserService userService;

    @Autowired
    public AuthController(OtpService otpService, UserService userService) {
        this.otpService = otpService;
        this.userService = userService;
    }

    @GetMapping(path = "/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping(path = "/login")
    public String doLogin(@RequestParam("phone") String phone,
                          @RequestParam(value = "request_code", required = false) boolean requestCode,
                          @RequestParam(value = "code", required = false) String code,
                          ModelMap modelMap) {
        User user = new User(); //todo
        user.setPhoneNumber(phone);

        if (requestCode) {
            otpService.sendCode(user);
            modelMap.put("message", "Code sent");
            return "redirect:/login";
        }

        modelMap.put("message", otpService.codeCorrect(user, code) ? "Code correct" : "COde incorrect");
        return "/login";
    }
}
