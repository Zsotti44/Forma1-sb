package com.f1sb.forma1sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Uzenet {

    @Id
    private int id;
    private Integer felhasznalo_id;
    private String uzenet;
    private Timestamp rogzites;

    public Uzenet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFelhasznalo_id() {
        return felhasznalo_id;
    }

    public void setFelhasznalo_id(Integer felhasznalo_id) {
        this.felhasznalo_id = felhasznalo_id;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }

    public Timestamp getRogzites() {
        return rogzites;
    }

    public void setRogzites(Timestamp rogzites) {
        this.rogzites = rogzites;
    }
}

