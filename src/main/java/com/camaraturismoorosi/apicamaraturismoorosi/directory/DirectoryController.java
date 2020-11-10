package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
}
