package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public void insertCompany(@RequestBody Company company) {
        directoryService.insertCompany(company);
    }
    
}
