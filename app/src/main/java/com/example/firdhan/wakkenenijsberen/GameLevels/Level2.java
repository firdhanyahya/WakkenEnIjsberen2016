package com.example.firdhan.wakkenenijsberen.GameLevels;

import com.example.firdhan.wakkenenijsberen.Dice;

/**
 * Created by James on 02/12/2016.
 */

public class Level2 implements ILevel{
    //Dobbelstenen variant voor level 2
    private final int[] wakken = {0,0,0,1,1,1};
    private final int[] ijsberen = {0,1,0,2,0,3};
    private final int[] pinguins = {6,5,0,3,0,1};
    //Geworpen dobbelstenen
    private int[] dices;
    //Aantalwakken, ijsberen en pinguins vastleggen
    private int aantalWakken;
    private int aantalIjsberen;
    private int aantalPinguins;
    //Voeg aantalwakken ijsberen en pinguins toe aan een array results voor antwoord
    private int[] results;

    //Methode om te werpen. Voeg alle geworpen dobbelstenen aan de array dices
    public void throwDice(int aantalDobbelstenen){
        Dice throwDices = new Dice(aantalDobbelstenen);
        throwDices.rollDices();
        this.dices = throwDices.getDices();
    }

    public int[] getAnswer(){
        //Array results -- [aantalWakken, aantalIjsberen, aantalPinguins]
        aantalWakken = 0;
        aantalIjsberen = 0;
        aantalPinguins = 0;

        for(int dice : dices){
            switch(dice){
                case 1:
                    aantalWakken += this.wakken[0];
                    aantalIjsberen += this.ijsberen[0];
                    aantalPinguins += this.pinguins[0];
                    break;
                case 2:
                    aantalWakken += this.wakken[1];
                    aantalIjsberen += this.ijsberen[1];
                    aantalPinguins += this.pinguins[1];
                    break;
                case 3:
                    aantalWakken += this.wakken[2];
                    aantalIjsberen += this.ijsberen[2];
                    aantalPinguins += this.pinguins[2];
                    break;
                case 4:
                    aantalWakken += this.wakken[3];
                    aantalIjsberen += this.ijsberen[3];
                    aantalPinguins += this.pinguins[3];
                    break;
                case 5:
                    aantalWakken += this.wakken[4];
                    aantalIjsberen += this.ijsberen[4];
                    aantalPinguins += this.pinguins[4];
                    break;
                case 6:
                    aantalWakken += this.wakken[5];
                    aantalIjsberen += this.ijsberen[5];
                    aantalPinguins += this.pinguins[5];
                    break;
            }
        }
        this.results = new int[3];
        results[0] = aantalWakken;
        results[1] = aantalIjsberen;
        results[2] = aantalPinguins;
        //Return aantalWakken, aantalIjsberen en aantalPinguins
        return this.results;
    }

    //Test Methode om de ogen van de dobbelstenen te weten.
    public int[] getDices(){
        return this.dices;
    }
}
