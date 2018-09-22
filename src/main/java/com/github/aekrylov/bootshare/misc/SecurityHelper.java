package com.github.aekrylov.bootshare.misc;

import com.github.aekrylov.bootshare.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 3:08 PM
 */
public class SecurityHelper {

    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || !(SecurityContextHolder.getContext().getAuthentication().getDetails() instanceof User)) {
            return null;
        }

        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
