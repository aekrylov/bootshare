package com.github.aekrylov.bootshare.service;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:59 PM
 */
public interface SmsGateway {

    void sendSms(String to, String text);
}
