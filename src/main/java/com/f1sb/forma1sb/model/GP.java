package com.f1sb.forma1sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class GP {
    @Id
    private Date datum;
    private String nev;
    private String helyszin;


    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public Date getDatum() {
        return datum;
    }

    public String getNev() {
        return nev;
    }

}
