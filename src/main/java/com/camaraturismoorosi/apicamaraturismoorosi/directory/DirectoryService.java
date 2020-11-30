package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Company;
import com.camaraturismoorosi.apicamaraturismoorosi.model.Error;

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
    @SuppressWarnings("unchecked")
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

    public Map<Integer, Error> checkNameEmailPhonesUnique(Company company) {
        Map<Integer, Error> errors = new HashMap<Integer, Error>();
        List<Company> companies = getAllCompanies();
        Optional<Company> uniqueName = null;
        if (company.getCompanyId() == null) {
            uniqueName = companies.stream()
                    .filter(actualCompany -> actualCompany.getCompanyName().equalsIgnoreCase(company.getCompanyName()))
                    .findFirst();
        } else {
            uniqueName = companies.stream()
                    .filter(actualCompany -> actualCompany.getCompanyName().equalsIgnoreCase(company.getCompanyName())
                            && !actualCompany.getCompanyId().equals(company.getCompanyId()))
                    .findFirst();
        }

        if (!uniqueName.isEmpty()) {
            errors.put(0, new Error("invalidCompanyName",
                    "El nombre de la compañía ya se encuentra registrado en el sistema"));
        }

        Optional<Company> uniqueEmail = null;
        if (company.getCompanyId() == null) {
            uniqueEmail = companies.stream().filter(actualCompany -> !actualCompany.getCompanyEmail().equals("")
                    && actualCompany.getCompanyEmail().equals(company.getCompanyEmail())).findFirst();
        } else {
            uniqueEmail = companies.stream()
                    .filter(actualCompany -> !actualCompany.getCompanyEmail().equals("")
                            && actualCompany.getCompanyEmail().equals(company.getCompanyEmail())
                            && !actualCompany.getCompanyId().equals(company.getCompanyId()))
                    .findFirst();
        }

        if (!uniqueEmail.isEmpty()) {
            errors.put(1, new Error("invalidCompanyEmail",
                    "El correo de la compañía ya se encuentra registrado en el sistema"));
        }

        if (company.getCompanyId() == null) {
            for (Company actualCompany : companies) {
                List<Map<String, String>> phones = actualCompany.getCompanyPhones();
                for (Map<String, String> phone : phones) {
                    String result = equalPhones(phone, company);
                    if (!result.equals("")) {
                        errors.put(2, new Error("invalidPhoneNumber", String
                                .format("El número de teléfono: %s ya se encuentra registrado en el sistema", result)));
                        return errors;
                    }
                }
            }
        } else {
            for (Company actualCompany : companies) {
                if (!actualCompany.getCompanyId().equals(company.getCompanyId())) {
                    List<Map<String, String>> phones = actualCompany.getCompanyPhones();
                    for (Map<String, String> phone : phones) {
                        String result = equalPhones(phone, company);
                        if (!result.equals("")) {
                            errors.put(2,
                                    new Error("invalidPhoneNumber", String.format(
                                            "El número de teléfono: %s ya se encuentra registrado en el sistema",
                                            result)));
                            return errors;
                        }
                    }
                }
            }
        }
        return errors;

    }

    private String equalPhones(Map<String, String> phone, Company newCompany) {
        for (Map<String, String> newCompanyPhone : newCompany.getCompanyPhones()) {
            if (newCompanyPhone.get("phone").equals(phone.get("phone"))) {
                return newCompanyPhone.get("phone");
            }
        }

        return "";
    }

}
