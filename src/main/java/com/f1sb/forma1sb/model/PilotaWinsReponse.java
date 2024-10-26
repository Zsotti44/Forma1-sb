package com.f1sb.forma1sb.model;

public class PilotaWinsReponse {
    private String pilota;
    private long wins;

    public PilotaWinsReponse(String pilota, long wins) {
        this.pilota = pilota;
        this.wins = wins;
    }

    public void setPilota(String pilota) {
        this.pilota = pilota;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public String getPilota() {
        return pilota;
    }

    public long getWins() {
        return wins;
    }
}
