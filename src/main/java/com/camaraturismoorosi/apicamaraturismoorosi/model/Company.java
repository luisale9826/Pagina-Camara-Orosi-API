package com.camaraturismoorosi.apicamaraturismoorosi.model;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.google.firebase.database.annotations.NotNull;
public class Company {

    private String companyId;
    @NotNull
    @NotBlank(message = "Company Name must be indicated")
    private String companyName;
    @Email(message = "Email must be valid")
    private String companyEmail;
    private List<Map<String, String>> companyPhones;
    @NotNull
    @NotBlank(message = "Company Category must be indicated")
    private String companyCategory;
    private String companyDescription;
    private String companyAddress;
    private String companyLocation;
    private String companyLogo;
    private String companyFacebookProfile;
    private String companyInstagramProfile;

    public Company() {
    }

    public Company(String companyName, String companyEmail, List<Map<String, String>> companyPhones, String companyCategory,
            String companyDescription, String companyAddress, String companyLocation, String companyLogo,
            String companyFacebookProfile, String companyInstagramProfile) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhones = companyPhones;
        this.companyCategory = companyCategory;
        this.companyDescription = companyDescription;
        this.companyLogo = companyLogo;
        this.companyAddress = companyAddress;
        this.companyLocation = companyLocation;
        this.companyFacebookProfile = companyFacebookProfile;
        this.companyInstagramProfile = companyInstagramProfile;
    }

    public Company(String companyId, String companyName, String companyEmail, List<Map<String, String>> companyPhones,
            String companyCategory, String companyDescription, String companyAddress, String companyLocation,
            String companyLogo, String companyFacebookProfile, String companyInstagramProfile) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhones = companyPhones;
        this.companyCategory = companyCategory;
        this.companyDescription = companyDescription;
        this.companyLogo = companyLogo;
        this.companyAddress = companyAddress;
        this.companyLocation = companyLocation;
        this.companyFacebookProfile = companyFacebookProfile;
        this.companyInstagramProfile = companyInstagramProfile;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public List<Map<String, String>> getCompanyPhones() {
        return companyPhones;
    }

    public void setCompanyPhones(List<Map<String, String>> companyPhones) {
        this.companyPhones = companyPhones;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanyFacebookProfile() {
        return companyFacebookProfile;
    }

    public void setCompanyFacebookProfile(String companyFacebookProfile) {
        this.companyFacebookProfile = companyFacebookProfile;
    }

    public String getCompanyInstagramProfile() {
        return companyInstagramProfile;
    }

    public void setCompanyInstagramProfile(String companyInstagramProfile) {
        this.companyInstagramProfile = companyInstagramProfile;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

}
