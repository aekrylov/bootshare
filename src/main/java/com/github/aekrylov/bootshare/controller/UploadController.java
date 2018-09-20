package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:21 AM
 */
@Controller
@RequestMapping(path = "/upload")
public class UploadController {

    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("ttl") int ttlDays) {
        String id = storageService.upload(file, Duration.ofDays(ttlDays));
        return "redirect:/get/"+id;
    }

    @GetMapping
    public String uploadForm() {
        return "upload";
    }
}
