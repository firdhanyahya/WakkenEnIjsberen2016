package com.example.firdhan.wakkenenijsberen.GameLevels;

/**
 * Created by James on 05/12/2016.
 */

public interface ILevel {
    void throwDice(int aantalDobbelstenen);

    int[] getAnswer();

    int[] getDices();
}
