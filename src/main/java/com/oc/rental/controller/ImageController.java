package com.oc.rental.controller;

import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private final Path imageDirectory = Paths.get("src/main/resources/static/rentals-images");

    @RequestMapping("/rentals-images")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            Path imagePath = imageDirectory.resolve(imageName).normalize();
            Resource imageResource = (Resource) new UrlResource(imagePath.toUri());

            if (imageResource != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
                return new ResponseEntity<>(imageResource, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}