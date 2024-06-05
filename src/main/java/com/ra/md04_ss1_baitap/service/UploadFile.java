package com.ra.md04_ss1_baitap.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
    public String uploadToLocal(MultipartFile multipartFile);
    public String uploadToFirebase(String localPath);
}
