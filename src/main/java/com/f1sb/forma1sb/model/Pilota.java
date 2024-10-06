package com.f1sb.forma1sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pilota {
    @Id
    private int az;
    private String nev;
    private Character nem;
    private String szuldat;
    private String nemzet;

    public void setAz(int az) {
        this.az = az;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setNem(Character nem) {
        this.nem = nem;
    }

    public void setSzuldat(String szuldat) {
        this.szuldat = szuldat;
    }

    public void setNemzet(String nemzet) {
        this.nemzet = nemzet;
    }

    public int getAz() {
        return az;
    }

    public String getNev() {
        return nev;
    }

    public Character getNem() {
        return nem;
    }

    public String getSzuldat() {
        return szuldat;
    }

    public String getNemzet() {
        return nemzet;
    }




}
