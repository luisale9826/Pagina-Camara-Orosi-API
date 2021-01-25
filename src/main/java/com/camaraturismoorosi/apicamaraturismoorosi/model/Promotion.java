package com.camaraturismoorosi.apicamaraturismoorosi.model;

import java.time.LocalDate;

public class Promotion {

    private String id;
    private String name;
    private String link;
    private LocalDate expirationDate;
    private LocalDate startDate;

    public Promotion() {
    }

    public Promotion(String id, String name, String link, LocalDate startDate, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.expirationDate = expirationDate;
        this.startDate = startDate;
    }

    public Promotion(String name, String link, LocalDate startDate, LocalDate expirationDate) {
        this.name = name;
        this.link = link;
        this.expirationDate = expirationDate;
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
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

    public String getId() {
        return id;
    }

    public void setCompanyId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Promotion [expirationDate=" + expirationDate + ", id=" + id + ", link=" + link + ", name=" + name
                + ", startDate=" + startDate + "]";
    }

}