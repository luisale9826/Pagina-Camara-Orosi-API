package com.camaraturismoorosi.apicamaraturismoorosi.promotion;

import java.util.ArrayList;
import java.util.List;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "visiter/promotion" })
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @GetMapping
    public List<Promotion> getPromotions() {
        return promotionService.getPromotions();
    }

    List<String> files = new ArrayList<String>();

    @PostMapping(value = "/savefile", consumes = { "multipart/form-data" })
    public ResponseEntity<String> handleFileUpload(@RequestParam("archivo") MultipartFile file,@RequestParam("nombre") String name) {
        System.out.println("Recibe imagen del front");
        String message;

         promotionService.insertPromotionFile(file,name);
         message = "Successfully uploaded!";
         return ResponseEntity.status(HttpStatus.OK).body(message);

   }
  
}


