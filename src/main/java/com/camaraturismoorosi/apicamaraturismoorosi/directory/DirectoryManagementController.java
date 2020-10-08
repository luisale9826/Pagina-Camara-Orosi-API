package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PutMapping(path = "{companyId}")
    @PreAuthorize("hasAuthority('directory:write')")
    public void updateCompany(@PathVariable("companyId") String companyId, @Valid @RequestBody Company company) {
        directoryService.updateCompany(companyId, company);
    }

    @DeleteMapping(path = "{companyId}")
    @PreAuthorize("hasAuthority('directory:write')")
    public void deleteCompany(@PathVariable("companyId") String companyId) {
        directoryService.deleteCompany(companyId);
    }

    @PreAuthorize("hasAuthority('directory:write')")
    public void insertCompany(@Valid @RequestBody Company company) {
        directoryService.insertCompany(company);
    }

}
