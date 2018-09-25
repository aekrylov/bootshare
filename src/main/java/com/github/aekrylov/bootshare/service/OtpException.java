package com.github.aekrylov.bootshare.service;

/**
 * General exception while sending an OTP code
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/25/18 8:52 PM
 */
public class OtpException extends RuntimeException {

    public OtpException(String message) {
        super(message);
    }

    public OtpException(Throwable cause) {
        super(cause);
    }

    public OtpException(String message, Throwable cause) {
        super(message, cause);
    }
}
