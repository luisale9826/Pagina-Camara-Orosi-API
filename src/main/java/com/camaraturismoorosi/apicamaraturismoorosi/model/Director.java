package com.camaraturismoorosi.apicamaraturismoorosi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Director {

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String major;

    public Director(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
