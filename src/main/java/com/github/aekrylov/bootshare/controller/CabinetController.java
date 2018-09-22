package com.github.aekrylov.bootshare.controller;

import com.github.aekrylov.bootshare.misc.SecurityHelper;
import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
        User currentUser = SecurityHelper.getCurrentUser(); //todo move to advice
        List<FileInfo> files = storageService.getAllFiles(currentUser);

        map.put("current_user", currentUser);
        map.put("files", files);

        return "cabinet";
    }
}
