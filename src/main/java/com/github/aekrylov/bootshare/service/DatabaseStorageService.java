package com.github.aekrylov.bootshare.service;

import com.github.aekrylov.bootshare.model.BlobFile;
import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.repository.BlobFileRepository;
import com.github.aekrylov.bootshare.repository.FileInfoRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
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
    private final BlobFileRepository blobFileRepository;
    private final Hashids hashids;

    @Autowired
    public DatabaseStorageService(FileInfoRepository fileInfoRepository, BlobFileRepository blobFileRepository, Hashids hashids) {
        this.fileInfoRepository = fileInfoRepository;
        this.blobFileRepository = blobFileRepository;
        this.hashids = hashids;
    }

    @Override
    public String upload(MultipartFile file, TemporalAmount ttl) {
        User currentUser = new User(); //todo current user
        currentUser.setId(1);
        try {
            FileInfo info = new FileInfo();
            String id = hashids.encode(currentUser.getId(), Instant.now().minus(Duration.ofDays(365*42)).toEpochMilli()); //todo better generation algorithm
            info.setId(id);
            
            //info.setOwner(currentUser); //todo current user
            info.setFilename(file.getOriginalFilename());
            info.setExpiresAt(Date.from(Instant.now().plus(ttl)));
            info = fileInfoRepository.save(info);

            BlobFile blob = new BlobFile();
            blob.setInfo(info);
            blob.setData(file.getBytes());
            blobFileRepository.save(blob);
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to persist file");
        }
    }

    @Override
    public String getFileName(String path) {
        return getFileInfo(path).getFilename();
    }

    @Override
    public FileInfo getFileInfo(String id) {
        return findFile(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public byte[] getFileAsBytes(String path) {
        return blobFileRepository.findById(path)
                .map(BlobFile::getData)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public List<FileInfo> getAllFiles(User user) {
        return fileInfoRepository.getByOwner(user);
    }

    private Optional<FileInfo> findFile(String id) {
        return fileInfoRepository.findById(id)
                .filter(file -> file.getExpiresAt().after(new Date())); //todo cleanup expired files here?
    }
}
