package com.github.aekrylov.bootshare.service;

import com.github.aekrylov.bootshare.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Stores file contents
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:16 PM
 */
public interface StorageBackend {

    /**
     * Persists file contents. This should be called after file info object is persisted
     * @param fileInfo file info object
     * @param file file objects
     * @throws IOException if IO exception occurs anywhere
     */
    void insert(FileInfo fileInfo, MultipartFile file) throws IOException;

    /**
     * Returns an input stream with file contents
     * @param fileId file id
     * @return input stream
     * @throws FileNotFoundException if file not found
     */
    InputStream getAsStream(String fileId);

    /**
     * Delete the file contents. Must be called after the file info is deleted
     * @param id file id
     * @throws FileNotFoundException if file not found
     */
    void delete(String id);
}
