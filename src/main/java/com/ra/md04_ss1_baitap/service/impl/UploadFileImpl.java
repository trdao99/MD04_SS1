package com.ra.md04_ss1_baitap.service.impl;

import com.google.cloud.storage.*;

import com.ra.md04_ss1_baitap.service.UploadFile;
import jakarta.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileImpl implements UploadFile {
    @Autowired
    private ServletContext servletContext;
    @Value("${bucker_name}")
    private String bucket_name;
    @Autowired
    private Storage storage;

    @Override
    public String uploadToLocal(MultipartFile multipartFile) {
        String path = servletContext.getRealPath("/uploads");

        File f = new File(path);
        System.out.println(f.getAbsolutePath());
        if(!f.exists()){
            f.mkdir();
        }
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String fileName = UUID.randomUUID()+multipartFile.getOriginalFilename();
        String localPath = f.getAbsolutePath()+File.separator+fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(localPath));
            return uploadToFirebase(localPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadToFirebase(String localPath) {
        Path path = Paths.get(localPath);
        String fileName = path.getFileName().toString();
        BlobId blobId = BlobId.of(bucket_name,fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(path));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
