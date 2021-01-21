package com.camaraturismoorosi.apicamaraturismoorosi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Value {

    @NotNull
    @NotBlank
    private String valueName;
    @NotNull
    @NotBlank
    private String valueDescription;

    public Value() {
    }

    public Value(String valueName, String valueDescription) {
        this.valueName = valueName;
        this.valueDescription = valueDescription;
    }

	public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValueDescription() {
        return valueDescription;
    }

    public void setValueDescription(String valueDescription) {
        this.valueDescription = valueDescription;
    }

}