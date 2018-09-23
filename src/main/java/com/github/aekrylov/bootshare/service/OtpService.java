package com.github.aekrylov.bootshare.service;

/**
 * Service for sending and checking one time passwords
 * <p>
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:09 PM
 */
public interface OtpService {

    /**
     * Sends confirmation code for a user, replacing with a new one if already exists
     *
     * @param phone phone number
     */
    void sendCode(String phone);

    /**
     * Sends confirmation code for a user, does not generate a new code if one already exists
     *
     * @param phone phone number
     */
    void resendCode(String phone);

    /**
     * Check if provided otp for the user is correct
     *
     * @param phone phone number
     * @param code otp code
     * @return true if correct
     * @throws OtpCodeNotFoundException if there's no OTP code for the user
     */
    boolean codeCorrect(String phone, String code);
}
