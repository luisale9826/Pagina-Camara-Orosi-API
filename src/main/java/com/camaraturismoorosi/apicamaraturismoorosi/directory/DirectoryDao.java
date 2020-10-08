package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.List;

public interface DirectoryDao {

    List<Company> getAllCompanies();
    
    void updateCompany(String companyId, Company company);

    void deleteCompany(String companyId);

    void insertCompany(Company company);
}
