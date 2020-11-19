package com.camaraturismoorosi.apicamaraturismoorosi.files;

import javax.lang.model.element.Name;

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

<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
    public String uploadFile(String path, MultipartFile image) throws Exception {
        return firebaseService.saveImage(path, image);
=======
>>>>>>> Stashed changes
    public String uploadFile(String path, MultipartFile image, String companyId) throws Exception {
        return firebaseService.saveImageCompany(path, image, companyId);
    }

<<<<<<< Updated upstream
=======
    public String uploadPromotion(String path, MultipartFile image, String name) throws Exception {
        return firebaseService.saveImagePromotion(path, image, name);
    }

>>>>>>> Stashed changes
    public void deleteFile(String path, String companyId) {
        firebaseService.deleteImage(path, companyId);
    }

    public String updateFile(String path, MultipartFile image, String companyId) throws Exception {
        return firebaseService.saveImageCompany(path, image, companyId);
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

}
