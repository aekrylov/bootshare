package com.github.aekrylov.bootshare.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:31 PM
 */
@Entity
@Table(name = "files")
public class FileInfo implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private Date expiresAt;

    @ManyToOne(optional = false)
    private User owner;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        if (!(o instanceof FileInfo)) return false;
        FileInfo uploadInfo = (FileInfo) o;
        return Objects.equals(getId(), uploadInfo.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
