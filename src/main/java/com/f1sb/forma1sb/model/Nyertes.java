package com.f1sb.forma1sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name = "nyertesek")
public class Nyertes {

    @Id
    private Date futam;
    private String csapat;
    private String nev;
    private String nemzet;
    private String helyszin;
    private String futamneve;

    public Date getFutam() {
        return futam;
    }

    public String getCsapat() {
        return csapat;
    }

    public String getNev() {
        return nev;
    }

    public String getNemzet() {
        return nemzet;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public String getFutamneve() {
        return futamneve;
    }
}
