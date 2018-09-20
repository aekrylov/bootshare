package com.github.aekrylov.bootshare.model;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:45 PM
 */
@Entity
public class BlobFile {

    @MapsId
    @OneToOne(optional = false)
    private FileInfo info;

    @Lob
    @Column(nullable = false)
    private byte[] data;

}
