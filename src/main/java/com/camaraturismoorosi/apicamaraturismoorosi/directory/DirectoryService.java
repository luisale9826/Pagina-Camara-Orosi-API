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
    private final FirebaseService fbService;

    @Autowired
    public DirectoryService(FirebaseService fbService) {
        this.fbService = fbService;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
        return objects.stream()
                .map(document -> new Company(document.getId(), document.getString("companyName"),
                        document.getString("companyEmail"), (List<Map<String, String>>) document.get("companyPhones"),
                        document.getString("companyCategory"), document.getString("companyDescription"),
                        document.getString("companyAddress"), document.getString("companyLocation"),
                        document.getString("companyLogo"), document.getString("companyFacebookProfile"),
                        document.getString("companyInstagramProfile")))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCompany(Company company) {
        Company updatedCompany = new Company(company.getCompanyName(), company.getCompanyEmail(),
                company.getCompanyPhones(), company.getCompanyCategory(), company.getCompanyDescription(),
                company.getCompanyAddress(), company.getCompanyLocation(), company.getCompanyLogo(),
                company.getCompanyFacebookProfile(), company.getCompanyInstagramProfile());
        fbService.updateObject(company.getCompanyId(), COLLECTION_NAME, updatedCompany);
    }

    @Override
    public void deleteCompany(String companyId) throws Exception {
        if (companyId.isEmpty() || companyId.isBlank()) {
            throw new Exception("Se debe especificar la compañía a ser eliminada");
        }
        fbService.deleteObject(companyId, COLLECTION_NAME);
    }

    @Override
    public String insertCompany(Company company) throws Exception {
        Map<String, Object> newCompany = new HashMap<>();
        newCompany.put("companyName", company.getCompanyName());
        newCompany.put("companyEmail", company.getCompanyEmail());
        newCompany.put("companyPhones", company.getCompanyPhones());
        newCompany.put("companyLogo", null);
        newCompany.put("companyCategory", company.getCompanyCategory());
        newCompany.put("companyDescription", company.getCompanyDescription());
        newCompany.put("companyAddress", company.getCompanyAddress());
        newCompany.put("companyLocation", company.getCompanyLocation());
        newCompany.put("companyFacebookProfile", company.getCompanyFacebookProfile());
        newCompany.put("companyInstagramProfile", company.getCompanyInstagramProfile());
        return fbService.insertObject(COLLECTION_NAME, newCompany);
    }

    public void updateCompanyLogo(String objectId, String url) {
        Map<String, Object> updatedFields = new HashMap<String, Object>();
        updatedFields.put("companyLogo", url);
        fbService.updateObjectSpecificFields(COLLECTION_NAME, objectId, updatedFields);
    }
}
