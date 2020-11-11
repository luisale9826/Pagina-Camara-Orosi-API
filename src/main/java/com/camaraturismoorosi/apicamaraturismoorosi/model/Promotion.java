package com.camaraturismoorosi.apicamaraturismoorosi.model;


public class Promotion {
  
    private String id;
    private String name;
    private String link;
    

    public Promotion() {
  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Promotion(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setCompanyId(String id) {
        this.id = id;
    }

    public Promotion(String id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

	

}