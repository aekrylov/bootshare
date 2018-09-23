package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 1:07 AM
 */
@Controller
@RequestMapping(path = "/f")
public class DownloadController {

    private final StorageService storageService;

    @Autowired
    public DownloadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(path = "/{id}")
    public String downloadPage(@PathVariable String id, ModelMap map) {
        map.put("id", id);
        map.put("filename", storageService.getFileInfo(id).getFilename());
        return "download";
    }

    @GetMapping(path = "/{id}/download")
    public ResponseEntity<?> download(@PathVariable String id) {
        FileInfo info = storageService.getFileInfo(id);

        Resource data = storageService.getFileAsResource(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                .filename(info.getFilename(), StandardCharsets.UTF_8)
                .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(data);
    }
}
