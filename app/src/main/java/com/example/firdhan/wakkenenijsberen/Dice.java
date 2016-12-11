package com.example.firdhan.wakkenenijsberen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by James on 25/11/2016.
 */

public class Dice {
    private int[] dices;
    private Random r;

    public Dice(int aantalDubbelstenen){
        dices = new int[aantalDubbelstenen];
    }

    public void rollDices(){
        Random randomDices = new Random();
        //Random dice 1 tm 6;
        for(int i = 0; i < dices.length; i++){
            dices[i] = randomDices.nextInt(6) + 1;
        }
    }

    public ArrayList<Integer> Roll(int amount){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < amount; i++){
            list.add(r.nextInt(6 - 1 + 1) + 1);
        }
        return list;
    }

    public int[] getDices(){
        if(dices != null){
            return this.dices;
        }
        return null;
    }
}
