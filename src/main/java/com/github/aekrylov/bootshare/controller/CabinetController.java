package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.misc.SecurityHelper;
import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 2:56 PM
 */
@Controller
public class CabinetController {

    private final StorageService storageService;

    @Autowired
    public CabinetController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(path = "/cabinet")
    public String cabinet(ModelMap map) {
        List<FileInfo> files = storageService.getAllFiles(SecurityHelper.getCurrentUser());

        map.put("files", files);

        return "cabinet";
    }

    @PostMapping(path = "/cabinet/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        FileInfo info = storageService.getFileInfo(id);
        if (!info.getOwner().equals(SecurityHelper.getCurrentUser())) {
            return ResponseEntity.status(403).build();
        }
        storageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
