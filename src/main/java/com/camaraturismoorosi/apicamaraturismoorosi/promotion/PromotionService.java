package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Error;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Promotion> getTopTenPromotions() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
        List<Promotion> promotions = objects.stream()
                .map(document -> new Promotion(document.getId(), document.getString("name"), document.getString("link"),
                        getDate(document.getString("startDate")), getDate(document.getString("expirationDate"))))
                .collect(Collectors.toList());
        promotions = deleteExpiredPromotions(promotions);
        Collections.sort(promotions, new Comparator<Promotion>() {
            public int compare(Promotion p1, Promotion p2) {
                if (p1.getStartDate() == null || p2.getStartDate() == null)
                    return 0;
                return p1.getStartDate().compareTo(p2.getStartDate());
            }
        });
        return promotions.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Promotion> getPromotions() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
        List<Promotion> promotions = objects.stream()
                .map(document -> new Promotion(document.getId(), document.getString("name"), document.getString("link"),
                        getDate(document.getString("startDate")), getDate(document.getString("expirationDate"))))
                .collect(Collectors.toList());
        promotions = deleteExpiredPromotions(promotions);
        return promotions;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deletePromotion(List<String> promotionId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            for (String id : promotionId) {
                filesService.deleteFile(FOLDER, id);
                fbService.deleteObject(id, COLLECTION_NAME);
            }
        } catch (Exception e) {
            result.put("Error al eleminar la promoción", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Promoción eliminada satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<Map<String, Object>> insertPromotion(MultipartFile file, String name, String startDate,
            String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (name.trim().isEmpty() || startDate.trim().isEmpty() || expirationDate.trim().isEmpty()
                    || file == null) {
                result.put("Error al insertar la promoción",
                        "El nombre no puede estar vacio, ni la fecha de inicio, ni la fecha de expiración o no se especificado una imagen para la promoción");
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
            }
            Map<String, Object> newPromotion = new HashMap<>();
            newPromotion.put("name", name);
            newPromotion.put("startDate", startDate);
            newPromotion.put("expirationDate", expirationDate);
            newPromotion.put("link", null);
            String promotionId = fbService.insertObject(COLLECTION_NAME, newPromotion);
            String link = filesService.uploadFile(FOLDER, file, promotionId);
            newPromotion.put("link", link);
            fbService.updateObjectSpecificFields(COLLECTION_NAME, promotionId, newPromotion);
        } catch (Exception e) {
            result.put("Error al insertar la promoción", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Promoción insertada correctamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updatePromotion(String promotionId, MultipartFile file, String name,
            String startDate, String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> updatedFields = new HashMap<>();
            filesService.updateFile(FOLDER, file, promotionId);
            if (name.trim().isEmpty() || startDate.trim().isEmpty() || expirationDate.trim().isEmpty()) {
                result.put("Error al modificar la promoción",
                        "El nombre no puede estar vacio, ni la fecha de inicio, ni la fecha de expiración o no se especificado una imagen para la promoción");
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
            }
            updatedFields.put("name", name);
            updatedFields.put("startDate", startDate);
            updatedFields.put("expirationDate", expirationDate);
            fbService.updateObjectSpecificFields(COLLECTION_NAME, promotionId, updatedFields);

        } catch (Exception e) {
            result.put("Error al modificar la promoción", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Promoción modificada correctamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updatePromotion(String promotionId, String name, String startDate,
            String expirationDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> updatedFields = new HashMap<>();
            if (name.trim().isEmpty() || startDate.trim().isEmpty() || expirationDate.trim().isEmpty()) {
                result.put("Error al modificar la promoción",
                        "El nombre no puede estar vacio, ni la fecha de inicio, ni la fecha de expiración o no se especificado una imagen para la promoción");
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
            }
            updatedFields.put("name", name);
            updatedFields.put("startDate", startDate);
            updatedFields.put("expirationDate", expirationDate);
            fbService.updateObjectSpecificFields(COLLECTION_NAME, promotionId, updatedFields);

        } catch (Exception e) {
            result.put("Error al modificar la promoción", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Promoción modificada correctamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateR = LocalDate.parse(date, formatter);
        return dateR;
    }

    public Map<Integer, Error> verifyDate(String startDate, String expirationDate) throws ParseException {
        Map<Integer, Error> errors = new HashMap<Integer, Error>();
        LocalDate begin = getDate(startDate);
        LocalDate end = getDate(expirationDate);

        if (begin.isAfter(end)) {
            errors.put(0,
                    new Error("invalidDate", "La fecha de incio no puede estar después de la fecha de expiración"));
        }

        if (end.isBefore(LocalDate.now())) {
            errors.put(0,
                    new Error("invalidDate", "La fecha de actual no puede estar antes de la fecha de expiración"));
        }
        return errors;
    }

    private List<Promotion> deleteExpiredPromotions(List<Promotion> promotions) {
        List<String> expiredPromotionsIds = new ArrayList<>();
        for (int i = 0; i < promotions.size(); i++) {
            Promotion prom = promotions.get(i);
            if (prom.getExpirationDate().isBefore(LocalDate.now())) {
                expiredPromotionsIds.add(prom.getId());
                promotions.remove(i);
            }
        }
        deletePromotion(expiredPromotionsIds);
        return promotions;
    }

}
