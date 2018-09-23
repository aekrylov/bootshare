package com.github.aekrylov.bootshare.model;

import javax.persistence.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:02 PM
 */
@Entity
public class FileSystemBlob {

    @Id
    private String fileId;

    @MapsId
    @OneToOne(cascade = CascadeType.REMOVE, optional = false)
    private FileInfo file;

    @Column(nullable = false, unique = true)
    private String relativePath;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public FileInfo getFile() {
        return file;
    }

    public void setFile(FileInfo file) {
        this.file = file;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
