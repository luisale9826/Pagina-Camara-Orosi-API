package com.camaraturismoorosi.apicamaraturismoorosi.webPageConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Board;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("visiter/cto/configuration")
public class WebPageConfigurationController {

    private final WebPageConfigurationService webPageConfigurationService;

    @Autowired
    public WebPageConfigurationController(WebPageConfigurationService webPageConfigurationService) {
        this.webPageConfigurationService = webPageConfigurationService;
    }

    @GetMapping(path = "images")
    public ResponseEntity<Map<String, Object>> getImage(@RequestParam("path") String path) {
        Map<String, Object> result = new HashMap<>();
        try {
            String url = webPageConfigurationService.getImage(path);
            result.put("url", url);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "text")
    public ResponseEntity<Map<String, Object>> getText(@RequestParam("text") String text) {
        Map<String, Object> result = new HashMap<>();
        try {
            String resultText = webPageConfigurationService.getText(text);
            result.put("text", resultText);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "board")
    public ResponseEntity<Map<String, Object>> getBoard() {
        Map<String, Object> result = new HashMap<>();
        try {
            Board board = webPageConfigurationService.getBoard();
            result.put("board", board);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "benefits")
    public ResponseEntity<Map<String, Object>> getBenefits() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<String> resultText = webPageConfigurationService.getBenefits();
            result.put("benefits", resultText);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "values")
    public ResponseEntity<Map<String, Object>> getValues() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Value> resultText = webPageConfigurationService.getValues();
            result.put("values", resultText);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
