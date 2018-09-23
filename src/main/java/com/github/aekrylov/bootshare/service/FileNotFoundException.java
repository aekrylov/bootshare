package com.github.aekrylov.bootshare.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:25 PM
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

    private final String filename;

    public FileNotFoundException(String filename) {
        this.filename = filename;
    }

    @Override
    public String getMessage() {
        return String.format("File '%s' was not found in the database", filename);
    }
}
