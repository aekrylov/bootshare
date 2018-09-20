package com.github.aekrylov.bootshare.service;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.temporal.TemporalAmount;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 10:53 PM
 */
public interface StorageService {

    String upload(MultipartFile file, TemporalAmount ttl);

    String getFileName(String path);

    FileInfo getFileInfo(String id);

    byte[] getFileAsBytes(String path);

    List<FileInfo> getAllFiles(User user);
}
