package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.FilePath;
import com.github.aekrylov.bootshare.repository.FilePathRepository;
import com.github.aekrylov.bootshare.service.FileNotFoundException;
import com.github.aekrylov.bootshare.service.StorageBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:20 PM
 */
@Service
@ConditionalOnExpression("'${storage.backend}' == 'fs' or '${storage.backend}' == 'hybrid'")
public class FileSystemStorageBackend implements StorageBackend {

    private final Path basePath;
    private final FilePathRepository repository;

    @Autowired
    public FileSystemStorageBackend(StorageProperties storageProperties, FilePathRepository repository) {
        this.basePath = storageProperties.getFs().getBasePath();
        if(!this.basePath.isAbsolute()) {
            throw new IllegalArgumentException("Base path for FS file system backend must be absolute");
        }
        this.repository = repository;
    }

    @Override
    public void insert(FileInfo fileInfo, MultipartFile file) throws IOException {
        FilePath blob = new FilePath();
        blob.setFile(fileInfo);
        Path relativePath = generatePath(fileInfo);
        blob.setRelativePath(relativePath.toString());

        Path absolutePath = basePath.resolve(relativePath);
        Files.createDirectories(absolutePath.getParent());
        file.transferTo(absolutePath.toFile());

        //info is stored only after the file is successfully persisted
        repository.save(blob);
    }

    @Override
    public InputStream getAsStream(String fileId) {
        try {
            return Files.newInputStream(getPath(fileId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource getAsResource(String fileId) {
        return new FileSystemResource(getPath(fileId).toFile());
    }

    @Override
    public void delete(String id) {
        try {
            Files.delete(getPath(id));
            repository.deleteById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPath(String fileId) {
        return repository.findById(fileId)
                .map(info -> basePath.resolve(info.getRelativePath()))
                .orElseThrow(() -> new FileNotFoundException(fileId));
    }

    private Path generatePath(FileInfo info) {
        //spread between directories
        return Paths.get(info.getId().substring(0, 2), info.getId().substring(2));
    }
}
