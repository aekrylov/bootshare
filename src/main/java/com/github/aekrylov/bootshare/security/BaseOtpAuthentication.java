package com.github.aekrylov.bootshare.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 1:34 AM
 */
public abstract class BaseOtpAuthentication extends AbstractAuthenticationToken {
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public BaseOtpAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public abstract String getPrincipal();
}
