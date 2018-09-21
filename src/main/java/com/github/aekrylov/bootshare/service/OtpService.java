package com.github.aekrylov.bootshare.service;

import com.github.aekrylov.bootshare.model.User;

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
     * @param user user
     */
    void sendCode(User user);

    /**
     * Sends confirmation code for a user, does not generate a new code if one already exists
     *
     * @param user user
     */
    void resendCode(User user);

    /**
     * Check if provided otp for the user is correct
     *
     * @param user user
     * @param code otp code
     * @return true if correct
     * @throws OtpCodeNotFoundException if there's no OTP code for the user
     */
    boolean codeCorrect(User user, String code);
}
