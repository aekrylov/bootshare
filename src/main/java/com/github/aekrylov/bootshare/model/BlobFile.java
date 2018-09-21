package com.github.aekrylov.bootshare.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:45 PM
 */
@Entity
public class BlobFile {

    @Id
    private String fileId;

    @MapsId
    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    private FileInfo info;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    public FileInfo getInfo() {
        return info;
    }

    public void setInfo(FileInfo info) {
        this.info = info;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlobFile)) return false;
        BlobFile blobFile = (BlobFile) o;
        return Objects.equals(getInfo(), blobFile.getInfo());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInfo());
    }
}
