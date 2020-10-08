package com.camaraturismoorosi.apicamaraturismoorosi.directory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.google.firebase.database.annotations.NotNull;


public class Company {

    private String companyId;
    @NotNull
    @NotBlank(message= "Company Name must be indicated")
    private String companyName;
    @Email(message = "Email must be valid")
    @NotNull
    @NotBlank(message= "Company Email must be indicated")
    private String companyEmail;
    @NotNull
    @NotBlank(message= "Company Phone must be indicated")
    private String companyPhone;
    @NotNull
    @NotBlank(message= "Company Category must be indicated")
    private String companyCategory;
    private String companyLogo;

    public Company() {
    }

    public Company(String companyId, String companyName, String companyEmail, String companyPhone, String companyCategory, String companyLogo) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyCategory = companyCategory;
        this.companyLogo = companyLogo;
    }

    public Company(String companyName, String companyEmail, String companyPhone, String companyCategory, String companyLogo) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyCategory = companyCategory;
        this.companyLogo = companyLogo;
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

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    @Override
    public String toString() {
        return "Company {" + "companyId=" + companyId + ", companyName='" + companyName + ", companyEmail=" + companyEmail + ", companyPhone=" + companyPhone + ", companyCategory=" + companyCategory +", companyLogo=" + companyLogo + '\'' + '}';
    }

}
