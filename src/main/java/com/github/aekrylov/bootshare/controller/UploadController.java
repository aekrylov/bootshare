package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:21 AM
 */
@Controller
@RequestMapping(path = {"/upload", "/"})
public class UploadController {

    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("ttl") int ttlDays) {
        storageService.upload(file, Duration.ofDays(ttlDays));
        return "redirect:/cabinet";
    }

    @GetMapping
    public String uploadForm() {
        return "upload";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleBigFiles(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("file_too_big", true);
        return "redirect:/upload";
    }
}
