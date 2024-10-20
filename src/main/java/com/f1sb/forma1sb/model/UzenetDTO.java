package com.f1sb.forma1sb.model;

import java.sql.Timestamp;

public class UzenetDTO {
    private int id;
    private String felhasznalonev;
    private String uzenet;
    private Timestamp rogzites;

    public UzenetDTO(int id, String felhasznalonev, String uzenet, Timestamp rogzites) {
        this.id = id;
        this.felhasznalonev = felhasznalonev;
        this.uzenet = uzenet;
        this.rogzites = rogzites;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    public void setRogzites(Timestamp rogzites) {
        this.rogzites = rogzites;
    }

    public int getId() {
        return id;
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public String getUzenet() {
        return uzenet;
    }

    public Timestamp getRogzites() {
        return rogzites;
    }
}
