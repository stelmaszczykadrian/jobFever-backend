package com.jobfever.controller;

import com.jobfever.service.S3Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/file")
@RestController
public class S3Controller {
    private S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        return s3Service.saveFile(file);
    }

    @GetMapping("/url")
    public String getUrl(@RequestParam("filename") String filename) {
        return s3Service.getUrl(filename);
    }

    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable("filename") String filename) {
        return s3Service.deleteFile(filename);
    }

    @GetMapping("/list")
    public List<String> getAllFiles() {
        return s3Service.listAllFiles();
    }
}
