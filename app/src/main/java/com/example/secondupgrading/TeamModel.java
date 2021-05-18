package com.example.secondupgrading;

public class TeamModel {
    private String TeamImage, TeamName, TeamDesc;

    public TeamModel(String teamImage, String teamName, String teamDesc) {
        this.TeamImage = teamImage;
        this.TeamName = teamName;
        this.TeamDesc = teamDesc;
    }

    public String getTeamImage() {
        return TeamImage;
    }

    public void setTeamImage(String teamImage) {
        TeamImage = teamImage;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTeamDesc() {
        return TeamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        TeamDesc = teamDesc;
    }
}
