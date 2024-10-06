package com.f1sb.forma1sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GP {
    @Id
    private String datum;
    private String nev;
    private String helyszin;


    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public String getDatum() {
        return datum;
    }

    public String getNev() {
        return nev;
    }

}
