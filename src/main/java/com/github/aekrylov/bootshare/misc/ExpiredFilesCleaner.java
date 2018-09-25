package com.github.aekrylov.bootshare.misc;

import com.github.aekrylov.bootshare.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 10:00 PM
 */
@Component
public class ExpiredFilesCleaner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final StorageService storageService;

    @Autowired
    public ExpiredFilesCleaner(StorageService storageService) {
        this.storageService = storageService;
    }

    @Scheduled(fixedRate = 3600 * 1000, initialDelay = 1000)
    public void cleanUp() {
        LOGGER.info("Cleaning up expired files...");
        int count = storageService.cleanUp();
        LOGGER.info("Successfully cleaned up {} files", count);
    }
}
