package com.github.aekrylov.bootshare.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:52 PM
 */
@Embeddable
public class OtpKey implements Serializable {

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false, length = 16)
    private String code;

    public OtpKey(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OtpKey)) return false;
        OtpKey otpKey = (OtpKey) o;
        return Objects.equals(getUser(), otpKey.getUser()) &&
                Objects.equals(getCode(), otpKey.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getCode());
    }
}
