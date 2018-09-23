package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.service.FileNotFoundException;
import com.github.aekrylov.bootshare.service.StorageBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hybrid backend that stores small files in the database and large files in the filesystem.
 * FS backend is considered faster, so it's checked for presence first.
 *
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:01 PM
 */
@Service
@Primary
@ConditionalOnProperty(name = "storage.backend", havingValue = "hybrid")
public class HybridStorageBackend implements StorageBackend {

    private final int threshold;
    private final DatabaseStorageBackend dbBackend;
    private final FileSystemStorageBackend fsBackend;

    @Autowired
    public HybridStorageBackend(StorageProperties storageProperties, DatabaseStorageBackend dbBackend, FileSystemStorageBackend fsBackend) {
        this.threshold = storageProperties.getHybrid().getThreshold();
        this.dbBackend = dbBackend;
        this.fsBackend = fsBackend;
    }

    @Override
    public void insert(FileInfo fileInfo, MultipartFile file) throws IOException {
        if (file.getSize() > threshold * 1024*1024) {
            fsBackend.insert(fileInfo, file);
        } else {
            dbBackend.insert(fileInfo, file);
        }
    }

    @Override
    public InputStream getAsStream(String fileId) {
        try {
            return fsBackend.getAsStream(fileId);
        } catch(FileNotFoundException ex) {
            return dbBackend.getAsStream(fileId);
        }
    }

    @Override
    public void delete(String id) {
        try{
            fsBackend.delete(id);
        } catch (FileNotFoundException ex) {
            dbBackend.delete(id);
        }
    }
}
