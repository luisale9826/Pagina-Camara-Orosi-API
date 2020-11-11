package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.List;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;

import org.springframework.web.multipart.MultipartFile;

public interface PromotionDao {

    List<Promotion> getPromotions();
    
    void updatePromotion(String promotionId, Promotion promotion);

    void deletePromotion(String promotionId);

    void insertPromotionFile(MultipartFile file, String name);

}
