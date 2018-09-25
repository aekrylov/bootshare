package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.misc.SecurityHelper;
import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.repository.FileInfoRepository;
import com.github.aekrylov.bootshare.service.FileNotFoundException;
import com.github.aekrylov.bootshare.service.StorageBackend;
import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:31 PM
 */
@Service
public class DatabaseStorageService implements StorageService {

    private final FileInfoRepository fileInfoRepository;
    private final FileIdGenerator fileIdGenerator;
    private final StorageBackend storageBackend;

    @Autowired
    public DatabaseStorageService(FileInfoRepository fileInfoRepository, FileIdGenerator fileIdGenerator, StorageBackend storageBackend) {
        this.fileInfoRepository = fileInfoRepository;
        this.fileIdGenerator = fileIdGenerator;
        this.storageBackend = storageBackend;
    }

    @Override
    @Transactional
    public FileInfo upload(MultipartFile file, TemporalAmount ttl) {
        User currentUser = SecurityHelper.getCurrentUser();
        try {
            FileInfo info = new FileInfo();
            String id = fileIdGenerator.generate(currentUser, file);
            info.setId(id);

            info.setOwner(currentUser);
            info.setFilename(file.getOriginalFilename());
            info.setExpiresAt(Date.from(Instant.now().plus(ttl)));
            info = fileInfoRepository.save(info);

            storageBackend.insert(info, file);
            return info;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to persist file");
        }
    }

    @Override
    public FileInfo getFileInfo(String id) {
        return findFile(id)
                .orElseThrow(() -> new FileNotFoundException(id));
    }

    @Override
    public void delete(String id) {
        storageBackend.delete(id);
        fileInfoRepository.deleteById(id);
    }

    @Override
    public InputStream getFileAsStream(String id) {
        return storageBackend.getAsStream(id);
    }

    @Override
    public Resource getFileAsResource(String id) {
        return storageBackend.getAsResource(id);
    }

    @Override
    public List<FileInfo> getAllFiles(User user) {
        return fileInfoRepository.getByOwner(user);
    }

    @Override
    public int cleanUp() {
        List<FileInfo> expired = fileInfoRepository.findAllExpired();
        expired.forEach(fileInfo -> delete(fileInfo.getId()));
        return expired.size();
    }

    private Optional<FileInfo> findFile(String id) {
        return fileInfoRepository.findById(id)
                .filter(file -> file.getExpiresAt().after(new Date()));
    }

}
