package com.github.aekrylov.bootshare.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 7:12 PM
 */
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String backend;

    private Fs fs = new Fs();

    private Hybrid hybrid = new Hybrid();

    static class Fs {
        private Path basePath;

        /**
         * Base path for sile system storage
         * @return
         */
        public Path getBasePath() {
            return basePath;
        }

        public void setBasePath(Path basePath) {
            this.basePath = basePath;
        }
    }

    static class Db {

    }

    static class Hybrid {


        private int threshold;

        /**
         * Files bigger than threshold (in MB) will be stored in the file system
         * @return
         */
        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }
    }

    /**
     * Specifies which storage backend to use (e.g. fs, db, hybrid)
     * @return
     */
    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }

    public Fs getFs() {
        return fs;
    }

    public void setFs(Fs fs) {
        this.fs = fs;
    }

    public Hybrid getHybrid() {
        return hybrid;
    }

    public void setHybrid(Hybrid hybrid) {
        this.hybrid = hybrid;
    }
}
