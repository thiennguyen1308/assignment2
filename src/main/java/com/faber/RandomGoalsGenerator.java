package com.faber;

import java.util.Random;

/**
 *
 * @author THIEN
 */
public class RandomGoalsGenerator {

    public int randomGoalInRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public int randomYellowCard() {
        Random rand = new Random();
        return rand.nextInt(2);//random from 0-1, 0 is no card, 1 is 1 card, 50%-50%
    }

    public int randomRedCard() {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(8);
        int randomNum2 = rand.nextInt(8);
        return randomNum1 == randomNum2 ? 1 : 0;//random red card, red card has a chance 4x times of yellow card, so 50%/4 = 12.5% = random a number from 0-7.
    }

    public int randomGoal() {
        Random rand = new Random();
        return rand.nextInt(2);//random goal in penalty, from 0-1
    }

    public int randomInRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
