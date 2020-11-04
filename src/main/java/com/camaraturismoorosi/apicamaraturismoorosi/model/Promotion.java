package com.camaraturismoorosi.apicamaraturismoorosi.model;


public class Promotion {
    private String id;
    private String title;
    private String companyName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Promotion(String id, String title, String companyName, String description) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.description = description;
    }

    public Promotion() {
  
    }

	

}