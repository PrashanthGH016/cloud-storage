package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    // ✅ Upload file
    @PostMapping("/file/upload")
public String uploadFile(@RequestParam("fileUpload") MultipartFile file,
                         Authentication auth,
                         Model model) {

    try {
        String username = auth.getName();
        int userId = userService.getUser(username).getUserid();

        File newFile = new File();

        newFile.setFilename(file.getOriginalFilename());
        newFile.setContenttype(file.getContentType());
        newFile.setFilesize(String.valueOf(file.getSize()));
        newFile.setUserid(userId);
        newFile.setFiledata(file.getBytes());

        fileService.uploadFile(newFile);

        model.addAttribute("success", true);

    } catch (Exception e) {
        model.addAttribute("success", false);
        model.addAttribute("errorMessage", "File upload failed: " + e.getMessage());
    }

    return "result"; // ✅ IMPORTANT
}
}