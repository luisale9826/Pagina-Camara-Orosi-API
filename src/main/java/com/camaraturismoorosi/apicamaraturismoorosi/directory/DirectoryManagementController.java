package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("management/cto/directory")
public class DirectoryManagementController {

    private final DirectoryService directoryService;
    private final FilesService filesService;

    @Autowired
    public DirectoryManagementController(DirectoryService directoryService, FilesService fileService) {
        this.directoryService = directoryService;
        this.filesService = fileService;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public void updateCompany(@Valid @RequestBody JsonNode body) {
        Company company = new ObjectMapper().convertValue(body.get("company"), Company.class);
        directoryService.updateCompany(company);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<String> deleteCompany(@RequestBody JsonNode body) {
        try {
            directoryService.deleteCompany(body.get("userId").asText());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al eliminar la compañía: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Compañía eliminada");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> insertCompany(@Valid @RequestBody Company company) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String companyId= directoryService.insertCompany(company);
            result.put("companyId", companyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al insertar compañía", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("message", "Compañía Insertada satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @PostMapping(path = "/image" )
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image,
            @RequestParam("companyId") String companyId) {
        try {
            String url = filesService.uploadFile("directory", image);
            directoryService.updateCompanyLogo(companyId, url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir la imagen al servidor" + e.getMessage());
        }
        return ResponseEntity.ok().body("Image subida al servidor");
    }

}