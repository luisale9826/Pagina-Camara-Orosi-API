package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.List;

public interface DirectoryDao {

    List<Company> getAllCompanies();
    
    void updateCompany(Company company);

    void deleteCompany(String companyId) throws Exception;

    void insertCompany(Company company);
}
