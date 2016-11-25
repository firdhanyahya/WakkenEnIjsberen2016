package com.example.firdhan.wakkenenijsberen;

import java.util.Random;

/**
 * Created by James on 25/11/2016.
 */

public class Dice {
    private int[] dices;

    public Dice(int aantalDubbelstenen){
        dices = new int[aantalDubbelstenen];
        Random randomDices = new Random();
        //Random dice 1 tm 6;
        for(int i = 0; i < dices.length; i++){
            dices[i] = randomDices.nextInt(6) + 1;
        }
    }

    public int[] getDices(){
        if(dices != null){
            return this.dices;
        }
        return null;
    }
}
