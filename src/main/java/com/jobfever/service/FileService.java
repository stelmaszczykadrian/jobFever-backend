package com.jobfever.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String saveFile(MultipartFile file);

    String getUrl(String filename);

    String deleteFile(String filename);


    List<String> listAllFiles();
}
