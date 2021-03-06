package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.List;

import com.camaraturismoorosi.apicamaraturismoorosi.model.Company;


public interface DirectoryDao {

    List<Company> getAllCompanies();
    
    void updateCompany(Company company);

    void deleteCompany(String companyId) throws Exception;

    String insertCompany(Company company) throws Exception;
}
