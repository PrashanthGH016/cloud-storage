package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private NoteService noteService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CredentialService credentialService;

    @GetMapping("/home")
    public String homeView(Model model, Authentication auth) {

        String username = auth.getName();
        int userId = userService.getUser(username).getUserid();

        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("credentials", credentialService.getCredentials(userId));

        return "home";
    }
}