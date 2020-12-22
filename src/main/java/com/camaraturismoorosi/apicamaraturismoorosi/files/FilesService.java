package com.camaraturismoorosi.apicamaraturismoorosi.files;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {

    private FirebaseService firebaseService;

    @Autowired
    public FilesService(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    public String uploadFile(String path, MultipartFile image, String fileId) throws Exception {
        return firebaseService.saveImage(path, image, fileId);
    }

    public void deleteFile(String path, String fileId) {
        firebaseService.deleteImage(path, fileId);
    }

    public String updateFile(String path, MultipartFile image, String fileId) throws Exception {
        return firebaseService.saveImage(path, image, fileId);
    }

}
