package com.f1sb.forma1sb.model;

public class Eredmeny {
    private String  id;
    private String datum;
    private Integer pilotaaz;
    private Integer helyezes;
    private String  hiba;
    private String  csapat;
    private String  tipus;
    private String  motor;

    public Eredmeny(String id, String datum, Integer pilotaaz, Integer helyezes, String hiba, String csapat, String tipus, String motor) {
        this.id = id;
        this.datum = datum;
        this.pilotaaz = pilotaaz;
        this.helyezes = helyezes;
        this.hiba = hiba;
        this.csapat = csapat;
        this.tipus = tipus;
        this.motor = motor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setPilotaaz(Integer pilotaaz) {
        this.pilotaaz = pilotaaz;
    }

    public void setHelyezes(Integer helyezes) {
        this.helyezes = helyezes;
    }

    public void setHiba(String hiba) {
        this.hiba = hiba;
    }

    public void setCsapat(String csapat) {
        this.csapat = csapat;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }
    public String getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public Integer getPilotaaz() {
        return pilotaaz;
    }

    public Integer getHelyezes() {
        return helyezes;
    }

    public String getHiba() {
        return hiba;
    }

    public String getCsapat() {
        return csapat;
    }

    public String getTipus() {
        return tipus;
    }

    public String getMotor() {
        return motor;
    }

}
