package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("management/cto/promotion")
public class PromotionManagementController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionManagementController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping(path = "/saveFile")
    @PreAuthorize("hasAuthority('promotion:write')")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile image,
            @RequestParam("name") String name) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            promotionService.insertPromotionFile(image, name);
        } catch (Exception e) {
            result.put("Error al subir la imagen", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Imagen subida al servidor");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

}