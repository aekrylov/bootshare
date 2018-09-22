package com.github.aekrylov.bootshare.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 1:35 AM
 */
public class OtpAuthenticationToken extends BaseOtpAuthentication {

    private final String phone;
    private final String code;

    public OtpAuthenticationToken(String phone, String code) {
        super(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.phone = phone;
        this.code = code;
    }

    @Override
    public String getCredentials() {
        return code;
    }

    @Override
    public String getPrincipal() {
        return phone;
    }
}
