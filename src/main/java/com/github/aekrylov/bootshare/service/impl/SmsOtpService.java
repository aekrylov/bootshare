package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.service.OtpCodeNotFoundException;
import com.github.aekrylov.bootshare.service.OtpService;
import com.github.aekrylov.bootshare.service.SmsGateway;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:31 PM
 */
@Service
public class SmsOtpService implements OtpService {

    private final Cache<User, String> codes;

    private final SmsGateway gateway;
    private final MessageSource messageSource;

    @Autowired
    public SmsOtpService(OtpProperties otpProperties, SmsGateway gateway, MessageSource messageSource) {
        codes = CacheBuilder.newBuilder()
                .expireAfterWrite(otpProperties.getCodeTtl())
                .build();
        this.gateway = gateway;
        this.messageSource = messageSource;
    }

    @Override
    public void sendCode(User user) {
        String code = CodeGenerator.generate(6);
        codes.put(user, code);
        sendCode(user.getPhoneNumber(), code);
    }

    @Override
    public void resendCode(User user) {
        String code = codes.getIfPresent(user);
        if (code == null)
            code = CodeGenerator.generate(6);

        //refresh ttl
        codes.put(user, code);
        sendCode(user.getPhoneNumber(), code);
    }

    @Override
    public boolean codeCorrect(User user, String code) {
        String presentCode = codes.getIfPresent(user);
        if (presentCode != null) {
            return code.equals(presentCode);
        }
        throw new OtpCodeNotFoundException();
    }

    private void sendCode(String phone, String code) {
        String message = messageSource.getMessage(
                "otp.sms.template",
                new Object[]{code},
                LocaleContextHolder.getLocale()
        );
        gateway.sendSms(phone, message);
    }

    private static class CodeGenerator {

        private static final char[] ALPHABET = "01234567890".toCharArray();
        private static final Random RANDOM = new Random();

        public static String generate(int length) {
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < length; i++) {
                code.append(ALPHABET[RANDOM.nextInt(ALPHABET.length)]);
            }
            return code.toString();
        }
    }
}
