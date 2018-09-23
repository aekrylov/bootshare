package com.github.aekrylov.bootshare.security;

import org.springframework.security.core.AuthenticationException;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 2:02 AM
 */
public class InvalidCodeException extends AuthenticationException {
    public InvalidCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidCodeException(String msg) {
        super(msg);
    }
}
