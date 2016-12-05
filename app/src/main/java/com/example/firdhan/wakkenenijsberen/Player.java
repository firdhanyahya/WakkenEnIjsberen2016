package com.example.firdhan.wakkenenijsberen;

/**
 * Created by James on 05/12/2016.
 */

public class Player {
    private String playerName;
    //Time in seconds is de tijd wanneer de speler een level heeft afgemaakt
    private int timeInSeconds;

    public Player(String playerName, int timeInSeconds) {
        this.playerName = playerName;
        this.timeInSeconds = timeInSeconds;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public int getTimeInSeconds(){
        return this.timeInSeconds;
    }
}
