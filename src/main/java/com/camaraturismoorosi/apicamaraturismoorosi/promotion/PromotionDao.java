package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.List;
import java.util.Map;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PromotionDao {

    List<Promotion> getPromotions();

    List<Promotion> getTopTenPromotions();
    
    ResponseEntity<Map<String, Object>> updatePromotion(String promotionId, MultipartFile file, String name, String startDate, String expirationDate);

    ResponseEntity<Map<String, Object>> updatePromotion(String promotionId, String name, String startDate, String expirationDate);

    ResponseEntity<Map<String, Object>> deletePromotion(List<String> promotionId);

    ResponseEntity<Map<String, Object>> insertPromotion(MultipartFile file, String name, String startDate, String expirationDate);

}
