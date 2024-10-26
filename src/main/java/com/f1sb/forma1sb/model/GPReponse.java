package com.f1sb.forma1sb.model;

public class GPReponse {
    private String GP;
    private long wins;

    public GPReponse(String GP, long wins) {
        this.GP = GP;
        this.wins = wins;
    }

    public void setGP(String GP) {
        this.GP = GP;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public String getGP() {
        return GP;
    }

    public long getWins() {
        return wins;
    }
}
