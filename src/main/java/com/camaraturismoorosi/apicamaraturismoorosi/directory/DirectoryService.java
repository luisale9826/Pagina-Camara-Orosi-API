package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryService implements DirectoryDao {

    private final String COLLECTION_NAME = "companies";
    private final String FOLDER = "directory";
    private final FirebaseService fbService;

    @Autowired
    public DirectoryService(FirebaseService fbService) {
        this.fbService = fbService;
    }

    @Override
    public List<Company> getAllCompanies() {
            List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
            return objects.stream()
                    .map(document -> new Company(document.getString("companyId"), document.getString("companyName"),
                            document.getString("companyEmail"), document.getString("companyPhone"),
                            document.getString("companyCategory"), document.getString("companyLogo")))
                    .collect(Collectors.toList());
    }

    @Override
    public void updateCompany(String companyId, Company company) {
        Company updatedCompany = new Company(company.getCompanyName(), company.getCompanyEmail(), company.getCompanyPhone(),
                            company.getCompanyCategory(), company.getCompanyLogo());
        fbService.updateObject(companyId, COLLECTION_NAME, updatedCompany);
    }

    @Override
    public void deleteCompany(String companyId) {
        fbService.deleteObject(companyId, COLLECTION_NAME);
    }

    @Override
    public void insertCompany(Company company) {
        String link = fbService.saveImage(FOLDER, company.getImage());
        Map<String, Object> newCompany = new HashMap<>();
        newCompany.put("companyName", company.getCompanyName());
        newCompany.put("companyEmail", company.getCompanyEmail());
        newCompany.put("companyPhone", company.getCompanyPhone());
        newCompany.put("companyLogo", link);
        fbService.insertObject(COLLECTION_NAME, newCompany);
    }
}
