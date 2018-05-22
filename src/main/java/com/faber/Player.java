package com.faber;

/**
 *
 * @author THIEN
 */
public class Player {

    private String name;
    private int goals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    //<editor-fold defaultstate="collapsed" desc="CHECK VALIDATE NAME">
    public boolean isValidateName(String name) {
        //Check length
        if (name.length() < 2 || name.startsWith("-") || name.endsWith("-")) {
            return false;
        }

        //Check contain non alphabet
        for (int i = 0; i < name.length(); i++) {
            char charAt2 = name.charAt(i);
            if (!Character.isLetter(charAt2) && !Character.toString(charAt2).equals("-")) {
                return false;
            }
        }

        return true;
    }
    //</editor-fold>ß
}
