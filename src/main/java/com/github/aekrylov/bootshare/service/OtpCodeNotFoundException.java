package com.github.aekrylov.bootshare.service;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 11:14 PM
 */
public class OtpCodeNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "OTP code not found";
    }
}
