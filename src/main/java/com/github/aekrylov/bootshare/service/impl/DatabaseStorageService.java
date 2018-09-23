package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.misc.SecurityHelper;
import com.github.aekrylov.bootshare.model.FileBlob;
import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.repository.BlobFileRepository;
import com.github.aekrylov.bootshare.repository.FileInfoRepository;
import com.github.aekrylov.bootshare.service.FileNotFoundException;
import com.github.aekrylov.bootshare.service.StorageService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
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
    @Transactional
    public FileInfo upload(MultipartFile file, TemporalAmount ttl) {
        User currentUser = SecurityHelper.getCurrentUser();
        try {
            FileInfo info = new FileInfo();
            String id = generateFileId(currentUser, file);
            info.setId(id);

            info.setOwner(currentUser);
            info.setFilename(file.getOriginalFilename());
            info.setExpiresAt(Date.from(Instant.now().plus(ttl)));
            info = fileInfoRepository.save(info);

            FileBlob blob = new FileBlob();
            blob.setInfo(info);
            blob.setData(file.getBytes());
            blobFileRepository.save(blob);
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
        blobFileRepository.deleteById(id);
    }

    @Override
    public byte[] getFileAsBytes(String id) {
        return blobFileRepository.findById(id)
                .map(FileBlob::getData)
                .orElseThrow(() -> new FileNotFoundException(id));
    }

    @Override
    public List<FileInfo> getAllFiles(User user) {
        return fileInfoRepository.getByOwner(user);
    }

    private Optional<FileInfo> findFile(String id) {
        return fileInfoRepository.findById(id)
                .filter(file -> file.getExpiresAt().after(new Date()));
    }

    private String generateFileId(User owner, MultipartFile file) {
        return hashids.encode(
                owner.getId(),
                fileInfoRepository.count()
        ); //todo better generation algorithm
    }
}
