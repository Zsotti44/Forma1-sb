package com.f1sb.forma1sb.model;

public class NationWinsResponse {
    private String nation;
    private long wins;

    public NationWinsResponse(String nation, long wins) {
        this.nation = nation;
        this.wins = wins;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }
}
