package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Company;

@RestController
@RequestMapping("visiter/cto/directory")
public class DirectoryController {

    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return directoryService.getAllCompanies();
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getCompanyById(@RequestParam("id") String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Company company = directoryService.getCompanyById(id);
            result.put("Company", company);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al buscar la compañía", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);

    }
    
}
