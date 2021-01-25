package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.List;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("visiter/cto/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @GetMapping(path = "/10")
    public List<Promotion> getTopTenPromotions() {
        return promotionService.getTopTenPromotions();
    }
    
    @GetMapping
    public List<Promotion> getPromotions() {
        return promotionService.getPromotions();
    }
}


