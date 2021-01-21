package com.camaraturismoorosi.apicamaraturismoorosi.model;

import javax.validation.constraints.NotNull;

public class Board {

    @NotNull
    private Director president;
    @NotNull
    private Director vicepresident;
    @NotNull
    private Director secretary;
    @NotNull
    private Director treasurer;
    @NotNull
    private Director vocal;
    @NotNull
    private Director fiscal;

    public Board(Director president, Director vicepresident, Director secretary, Director treasurer, Director vocal,
            Director fiscal) {
        this.president = president;
        this.vicepresident = vicepresident;
        this.secretary = secretary;
        this.treasurer = treasurer;
        this.vocal = vocal;
        this.fiscal = fiscal;
    }

    public Board() {}

    public Director getPresident() {
        return president;
    }

    public void setPresident(Director president) {
        this.president = president;
    }

    public Director getVicepresident() {
        return vicepresident;
    }

    public void setVicepresident(Director vicepresident) {
        this.vicepresident = vicepresident;
    }

    public Director getSecretary() {
        return secretary;
    }

    public void setSecretary(Director secretary) {
        this.secretary = secretary;
    }

    public Director getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(Director treasurer) {
        this.treasurer = treasurer;
    }

    public Director getVocal() {
        return vocal;
    }

    public void setVocal(Director vocal) {
        this.vocal = vocal;
    }

    public Director getFiscal() {
        return fiscal;
    }

    public void setFiscal(Director fiscal) {
        this.fiscal = fiscal;
    }

}
