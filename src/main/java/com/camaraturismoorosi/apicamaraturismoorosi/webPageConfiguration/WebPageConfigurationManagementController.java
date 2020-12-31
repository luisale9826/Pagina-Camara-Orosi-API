package com.camaraturismoorosi.apicamaraturismoorosi.webPageConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("management/cto/configuration")
public class WebPageConfigurationManagementController {

    private WebPageConfigurationService wConfigService;

    @Autowired
    public WebPageConfigurationManagementController(WebPageConfigurationService wConfigService) {
        this.wConfigService = wConfigService;
    }

    @PostMapping(path = "image")
    @PreAuthorize("hasAuthority('configuration:write')")
    public ResponseEntity<Map<String, Object>> updateImage(@RequestParam("path") String path,
            @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            wConfigService.updateImage(path, file);
            result.put("message", "Se ha editado la Junta Directiva");
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al editar la Junta Directiva", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(path = "board")
    @PreAuthorize("hasAuthority('configuration:write')")
    public ResponseEntity<Map<String, Object>> editBoard(@Valid @RequestBody Board board) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            wConfigService.editBoard(board);
            result.put("message", "Se ha editado la Junta Directiva");
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al editar la Junta Directiva", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "text")
    @PreAuthorize("hasAuthority('configuration:write')")
    public ResponseEntity<Map<String, Object>> editText(@RequestParam("position") String position,
            @RequestParam("text") String text) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            wConfigService.editText(position, text);
            result.put("message", "Se ha editado el texto");
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al editar el texto", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "benefits")
    @PreAuthorize("hasAuthority('configuration:write')")
    public ResponseEntity<Map<String, Object>> editBenefits(@RequestBody List<String> benefits) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            wConfigService.editBenefits(benefits);
            result.put("message", "Se han editando los beneficios");
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al editar los benefiios", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
