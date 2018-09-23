package com.github.aekrylov.bootshare.misc;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.repository.FileInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:00 PM
 */
@Component
public class ExpiredFilesCleaner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final FileInfoRepository fileInfoRepository;

    @Autowired
    public ExpiredFilesCleaner(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @Scheduled(fixedRate = 5 * 1000, initialDelay = 1000)
    public void cleanUp() {
        LOGGER.info("Cleaning up expired files...");
        List<FileInfo> expired = fileInfoRepository.findAllExpired();
        fileInfoRepository.deleteAll(expired);
        LOGGER.info("Successfully cleaned up {} files", expired.size());
    }
}
