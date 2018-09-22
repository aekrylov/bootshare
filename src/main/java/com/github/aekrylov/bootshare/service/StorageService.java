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

    /**
     * Saves a file to the database
     *
     * @param file file to save
     * @param ttl  time-to-live, i.e. how soon this file will be expired
     * @return file info
     */
    FileInfo upload(MultipartFile file, TemporalAmount ttl);

    /**
     * Returns file info (i.e. name, author etc)
     *
     * @param id file id
     * @return file info object
     * @throws FileNotFoundException if the file is not found
     */
    FileInfo getFileInfo(String id);

    /**
     * Load file contents as bytes
     *
     * @param id file id
     * @return file contents
     * @throws FileNotFoundException if the file is not found
     */
    byte[] getFileAsBytes(String id);

    /**
     * Returns all files uploaded by a given user
     * @param user user to search for
     * @return list of all files uploaded by the user
     */
    List<FileInfo> getAllFiles(User user);
}
