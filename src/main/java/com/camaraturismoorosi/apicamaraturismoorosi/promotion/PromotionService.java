package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PromotionService implements PromotionDao {

    private final String COLLECTION_NAME = "promotions";
    private final String FOLDER = "promotion";
    private final FilesService filesService;
    private final FirebaseService fbService;

    @Autowired
    public PromotionService(FilesService filesService, FirebaseService fbService) {
        this.filesService = filesService;
        this.fbService = fbService;
    }

    @Override
    public List<Promotion> getPromotions() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
            return objects.stream()
                    .map(document -> new Promotion(document.getString("name"), document.getString("link")))
                    .collect(Collectors.toList());
    }

    @Override
    public void updatePromotion(String promotionId, Promotion promotion) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletePromotion(String promotionId) {
        // TODO Auto-generated method stub

    }

    public void insertPromotionFile(MultipartFile file, String name) {
        String link;
        try {
            link = filesService.uploadPromotion(FOLDER, file, name);
            Map<String, Object> newPromotion = new HashMap<>();
            newPromotion.put("name", name);
            newPromotion.put("link", link);
            fbService.insertObject(COLLECTION_NAME, newPromotion);

        } catch (Exception e) {
         
            e.printStackTrace();
        }
        
   }

}
