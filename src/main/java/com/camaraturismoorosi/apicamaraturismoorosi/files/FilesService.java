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

    public String uploadFile(String path, MultipartFile image, String companyId) throws Exception {
        return firebaseService.saveImage(path, image, companyId);
    }

    public void deleteFile(String path, String companyId) {
        firebaseService.deleteImage(path, companyId);
    }

    public String updateFile(String path, MultipartFile image, String companyId) throws Exception {
        return firebaseService.saveImage(path, image, companyId);
    }

}
