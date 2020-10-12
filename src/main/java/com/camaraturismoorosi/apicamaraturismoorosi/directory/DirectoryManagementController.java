package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping
    @PreAuthorize("hasAuthority('directory:write')")
    public void insertCompany(@Valid @RequestBody Company company) {
        directoryService.insertCompany(company);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('directory:write')")
    public void insertImage(@RequestParam("image") MultipartFile image) {
        directoryService.insertImage(image);
    }

}
