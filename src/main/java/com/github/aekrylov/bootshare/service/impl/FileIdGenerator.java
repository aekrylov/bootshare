package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.repository.FileInfoRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoField;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:13 PM
 */
@Component
public class FileIdGenerator {

    private static final Instant START_DATE = Instant.ofEpochSecond(1535760000); //Sep 1st 2018

    private final Hashids hashids;
    private final FileInfoRepository fileInfoRepository;

    @Autowired
    public FileIdGenerator(Hashids hashids, FileInfoRepository fileInfoRepository) {
        this.hashids = hashids;
        this.fileInfoRepository = fileInfoRepository;
    }

    public String generate(User owner, MultipartFile file) {
        return hashids.encode(
                owner.getId(),
                Duration.between(START_DATE, Instant.now()).getSeconds(),
                Instant.now().get(ChronoField.MILLI_OF_SECOND)
        ); //todo better generation algorithm
    }
}
