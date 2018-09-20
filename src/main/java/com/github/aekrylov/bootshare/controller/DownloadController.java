package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 1:07 AM
 */
@Controller
@RequestMapping(path = "/get")
public class DownloadController {

    private final StorageService storageService;

    @Autowired
    public DownloadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(path = "/{id}")
    public String downloadPage(@PathVariable String id, ModelMap map) {
        map.put("id", id);
        map.put("filename", storageService.getFileName(id));
        return "download";
    }

    @GetMapping(path = "/{id}/download")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {
        FileInfo info = storageService.getFileInfo(id);
        response.addHeader("Content-Disposition", "attachment; filename="+info.getFilename());
        response.getOutputStream().write(storageService.getFileAsBytes(id));
        response.getOutputStream().flush();
    }
}
