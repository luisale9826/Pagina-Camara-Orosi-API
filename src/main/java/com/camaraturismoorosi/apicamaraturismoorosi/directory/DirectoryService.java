package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseConfig;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryService implements DirectoryDao {

    private final String COLLECTION_NAME = "companies";
    private final FirebaseConfig fbConfig;

    @Autowired
    public DirectoryService(FirebaseConfig fbConfig) {
        this.fbConfig = fbConfig;
    }

    @Override
    public List<Company> getAllCompanies() {
        Firestore fbInstance = fbConfig.getFirebaseDatabase();
        ApiFuture<QuerySnapshot> query = fbInstance.collection(COLLECTION_NAME).get();
        List<Company> companies = null;
        try {
            companies = query.get().getDocuments()
                .stream()
                .map(document -> 
                    new Company(
                        document.getString("companyId"),
                        document.getString("companyName"),
                        document.getString("companyEmail"),
                        document.getString("companyPhone"),
                        document.getString("companyCategory"),
                        document.getString("companyLogo")
                    )
                ).collect(Collectors.toList());
            fbInstance.close();
        } catch(Exception e) {
            System.out.println(e);
        }     
        return  companies;
    }

    @Override
    public void updateCompany(String companyId, Company company) {
        try {
            Firestore fbInstance = fbConfig.getFirebaseDatabase();
            fbInstance.collection(COLLECTION_NAME).document(companyId).set(new Company(company.getCompanyName(), company.getCompanyEmail(), company.getCompanyPhone(), company.getCompanyCategory(), company.getCompanyLogo()));
            fbInstance.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public void deleteCompany(String companyId) {
        try {
            Firestore fbInstance = fbConfig.getFirebaseDatabase();
            fbInstance.collection(COLLECTION_NAME).document(companyId).delete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertCompany(Company company) {
        try {
            Map<String, Object> newCompany = new HashMap<>();
                newCompany.put("companyName", company.getCompanyName());
                newCompany.put("companyEmail", company.getCompanyEmail());
                newCompany.put("companyPhone", company.getCompanyPhone());
                newCompany.put("companyLogo", company.getCompanyLogo());
                Firestore fbInstance = fbConfig.getFirebaseDatabase();
            fbInstance.collection(COLLECTION_NAME).add(newCompany);
            fbInstance.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        
    }
}
