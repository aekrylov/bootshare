package com.github.aekrylov.bootshare.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * One Time Password codes
 *
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:46 PM
 */
@Entity
public class OtpCode {

    @EmbeddedId
    private OtpKey id;

    @Column(nullable = false)
    private Date expiresAt;

    public OtpKey getId() {
        return id;
    }

    public void setId(OtpKey id) {
        this.id = id;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OtpCode)) return false;
        OtpCode otpCode = (OtpCode) o;
        return Objects.equals(getId(), otpCode.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
