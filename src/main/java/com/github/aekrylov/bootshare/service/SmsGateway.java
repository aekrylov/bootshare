package com.github.aekrylov.bootshare.service;

/**
 * Gateway for sending SMS messages
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:59 PM
 */
public interface SmsGateway {

    /**
     * Sends an SMS to specified number
     * @param to phone number in international format
     * @param text message text, size should comply with SMS standards
     * @throws OtpException if an exception occurs while sending sms
     */
    void sendSms(String to, String text);
}
