package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    @PreAuthorize("hasAuthority('promotion:write')")
    public ResponseEntity<Map<String, Object>> savePromotion(@RequestParam("file") MultipartFile image,
            @RequestParam("name") String name, @RequestParam("startDate") String startDate,
            @RequestParam("expirationDate") String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<Integer, Error> errors = promotionService.verifyDate(startDate, expirationDate);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            promotionService.insertPromotion(image, name, startDate, expirationDate);
        } catch (Exception e) {
            result.put("Error al insertar la promoción", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Se he insertado la promoción");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('promotion:write')")
    public ResponseEntity<Map<String, Object>> updatePromotion(@RequestParam("promocionId") String promotionId,
            @RequestParam("file") MultipartFile image, @RequestParam("name") String name,
            @RequestParam("startDate") String startDate, @RequestParam("expirationDate") String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<Integer, Error> errors = promotionService.verifyDate(startDate, expirationDate);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            promotionService.updatePromotion(promotionId, image, name, startDate, expirationDate);
        } catch (Exception e) {
            result.put("Error al modificar la promoción", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Se ha modificado la promoción");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "/noImage")
    @PreAuthorize("hasAuthority('promotion:write')")
    public ResponseEntity<Map<String, Object>> updatePromotion(@RequestParam("promocionId") String promotionId, @RequestParam("name") String name,
            @RequestParam("startDate") String startDate, @RequestParam("expirationDate") String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<Integer, Error> errors = promotionService.verifyDate(startDate, expirationDate);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            promotionService.updatePromotion(promotionId, name, startDate, expirationDate);
        } catch (Exception e) {
            result.put("Error al modificar la promoción", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Se ha modificado la promoción");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('promotion:write')")
    public ResponseEntity<Map<String, Object>> deletePromotion(@RequestBody List<String> deletedPromotions) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            promotionService.deletePromotion(deletedPromotions);
        } catch (Exception e) {
            result.put("Error al eliminar la promoción", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Se ha eliminado la promoción");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

}