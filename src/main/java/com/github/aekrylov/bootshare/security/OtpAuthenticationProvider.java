package com.github.aekrylov.bootshare.security;

import com.github.aekrylov.bootshare.service.OtpCodeNotFoundException;
import com.github.aekrylov.bootshare.service.OtpService;
import com.github.aekrylov.bootshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 1:23 AM
 */
@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final OtpService otpService;
    private final UserService userService;

    @Autowired
    public OtpAuthenticationProvider(OtpService otpService, UserService userService) {
        this.otpService = otpService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;
        String phone = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();

        try {
            if (!otpService.codeCorrect(phone, code)) {
                throw new BadCredentialsException("OTP code invalid");
            }
        } catch (OtpCodeNotFoundException ex) {
            throw new BadCredentialsException("OPT code was not sent");
        }

        OtpAuthenticationToken token = new OtpAuthenticationToken(phone, code);
        token.setAuthenticated(true);
        token.setDetails(userService.getByPhoneOrCreate(phone));
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
