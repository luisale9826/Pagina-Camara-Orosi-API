package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService implements PromotionDao {

    private final String COLLECTION_NAME = "promotions";
    private final String FOLDER = "promotion";
    private final FirebaseService fbService;

    @Autowired
    public PromotionService(FirebaseService fbService) {
        this.fbService = fbService;
    }


    @Override
    public void insertPromotion(Promotion promotion) {
  
       //String link = fbService.saveImage(FOLDER, company.getImage());
        Map<String, Object> newPromotion = new HashMap<>();
        newPromotion.put("title", promotion.getTitle());
        newPromotion.put("companyName", promotion.getCompanyName());
        newPromotion.put("description", promotion.getDescription());
        //newPromotion.put("companyLogo", link);
        fbService.insertObject(COLLECTION_NAME, newPromotion);
    }

    @Override
    public List<Promotion> getPromotions() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
            return objects.stream()
                    .map(document -> new Promotion(document.getString("id"), document.getString("title"),
                            document.getString("companyName"), document.getString("description")))
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
}
