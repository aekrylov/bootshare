package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.misc.SecurityHelper;
import com.github.aekrylov.bootshare.model.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 5:14 PM
 */
@ControllerAdvice
public class CurrentUserAdvice {

    @ModelAttribute("current_user")
    public User currentUser() {
        return SecurityHelper.getCurrentUser();
    }
}
