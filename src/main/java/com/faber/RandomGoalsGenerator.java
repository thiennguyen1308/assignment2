package com.faber;

import java.util.Random;

/**
 *
 * @author THIEN
 */
public class RandomGoalsGenerator {

//    public int generateGoal(boolean isHigherRankedTeam, int diffTeam) {
//        if (isHigherRankedTeam) {
//            return randomNumber(0, 7);
//        }
//        return randomNumber(0, 7) - diffTeam;
//    }
    public int randomGoalInRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public int randomYellowCard() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    public int randomRedCard() {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(8);
        int randomNum2 = rand.nextInt(8);
        return randomNum1 == randomNum2 ? 1 : 0;
    }

   public int randomGoal() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    public int randomInRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
