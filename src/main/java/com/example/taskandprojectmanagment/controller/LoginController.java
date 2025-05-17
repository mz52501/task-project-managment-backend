package com.example.taskandprojectmanagment.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
class LoginController {

    @GetMapping(value = "/home")
    public String loadForm() {
        return "home";
    }

    @GetMapping(value = "/bla")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String loadForm2() {
        return "home";
    }

    @GetMapping(value = "/blabla" )
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public String load() {
        return "home";
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            String reactPublicPath = "C:\\master_degree_study\\master_thesis\\backend\\task-and-project-managment\\src\\main\\resources\\static\\images\\";
            Path filePath = Paths.get(reactPublicPath).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
