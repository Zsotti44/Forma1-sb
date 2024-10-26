package com.f1sb.forma1sb.model;

public class TeamWinsResponse {
    private String team;
    private long wins;

    public TeamWinsResponse(String team, long wins) {
        this.team = team;
        this.wins = wins;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public String getTeam() {
        return team;
    }

    public long getWins() {
        return wins;
    }
}
