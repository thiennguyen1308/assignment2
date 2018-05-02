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

    public static int randomGoalInRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
