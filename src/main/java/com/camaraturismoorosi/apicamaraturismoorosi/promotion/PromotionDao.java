package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.List;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;

public interface PromotionDao {

    List<Promotion> getPromotions();
    
    void updatePromotion(String promotionId, Promotion promotion);

    void deletePromotion(String promotionId);

    void insertPromotion(Promotion promotion) throws Exception;
}
