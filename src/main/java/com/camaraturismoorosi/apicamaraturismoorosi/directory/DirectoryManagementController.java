package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/cto/directory")
public class DirectoryManagementController {

    private final DirectoryService directoryService;

    @Autowired
    public DirectoryManagementController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public void updateCompany(@Valid @RequestBody JsonNode body) {
        String file = body.get("file").asText();
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
            return ResponseEntity.status(500)
            .body("Error al eliminar la compañía: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Compañía eliminada");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public void insertCompany(@Valid @RequestBody JsonNode body) {
        String file = body.get("file").asText();
        Company company = new ObjectMapper().convertValue(body.get("company"), Company.class);
        directoryService.insertCompany(company);
    }

}