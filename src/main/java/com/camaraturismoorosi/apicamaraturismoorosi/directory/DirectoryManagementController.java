package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.camaraturismoorosi.apicamaraturismoorosi.files.FilesService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Company;
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
@RequestMapping("management/cto/directory")
public class DirectoryManagementController {

    private final DirectoryService directoryService;
    private final FilesService filesService;
    private final String FOLDER_NAME = "directory";

    @Autowired
    public DirectoryManagementController(DirectoryService directoryService, FilesService fileService) {
        this.directoryService = directoryService;
        this.filesService = fileService;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> updateCompany(@Valid @RequestBody Company company) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<Integer, Error> errors = directoryService.checkNameEmailPhonesUnique(company);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
            }
            directoryService.updateCompany(company);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al modifica compañía", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Compañía Modificada satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "image")
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> updateImage(@RequestParam("image") MultipartFile image,
            @RequestParam("companyId") String companyId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String url = filesService.updateFile(FOLDER_NAME, image, companyId);
            directoryService.updateCompanyLogo(companyId, url);
        } catch (Exception e) {
            result.put("Error al editar la imagen", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Imagen editada en el servidor");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> deleteCompany(@RequestBody String companyId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            filesService.deleteFile(FOLDER_NAME, companyId);
            directoryService.deleteCompany(companyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al subir la imagen", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Imagen subida al servidor");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> insertCompany(@Valid @RequestBody Company company) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<Integer, Error> errors = directoryService.checkNameEmailPhonesUnique(company);
            if (!errors.isEmpty()) {
                result.put("errors", errors);
                return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
            }
            String companyId = directoryService.insertCompany(company);
            result.put("companyId", companyId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al insertar compañía", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Compañía Insertada satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @PostMapping(path = "image")
    @PreAuthorize("hasAuthority('directory:write')")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("image") MultipartFile image,
            @RequestParam("companyId") String companyId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String url = filesService.uploadFile(FOLDER_NAME, image, companyId);
            directoryService.updateCompanyLogo(companyId, url);
        } catch (Exception e) {
            result.put("Error al subir la imagen", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Imagen subida al servidor");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

}