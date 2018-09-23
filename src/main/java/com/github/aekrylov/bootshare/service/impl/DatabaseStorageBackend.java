package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.repository.RawFileBlobRepository;
import com.github.aekrylov.bootshare.service.StorageBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:17 PM
 */
@Service
@ConditionalOnExpression("'${storage.backend}' == 'db' or '${storage.backend}' == 'hybrid'")
public class DatabaseStorageBackend implements StorageBackend {

    private final RawFileBlobRepository repository;

    @Autowired
    public DatabaseStorageBackend(RawFileBlobRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insert(FileInfo fileInfo, MultipartFile file) throws IOException {
        repository.insert(fileInfo.getId(), file.getInputStream());
    }

    @Override
    public InputStream getAsStream(String fileId) {
        return repository.findById(fileId);
    }

    @Override
    public void delete(String id) {
        //file blob is deleted automatically when the file info is deleted,
    }
}
