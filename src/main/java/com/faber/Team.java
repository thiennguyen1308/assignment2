package com.faber;

/**
 *
 * @author THIEN
 */
public class Team {

    private String name;
    private int ranking;
    private int yellowCardScore;
    private int redCardScore;
    private Player player1;
    private Player player2;

    public Team() {
    }

    public Team(String name, int ranking, int yellowCardScore, int redCardScore, Player player1, Player player2) {
        this.name = name;
        this.ranking = ranking;
        this.yellowCardScore = yellowCardScore;
        this.redCardScore = redCardScore;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Team(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getYellowCardScore() {
        return yellowCardScore;
    }

    public void setYellowCardScore(int yellowCardScore) {
        this.yellowCardScore = yellowCardScore;
    }

    public int getRedCardScore() {
        return redCardScore;
    }

    public void setRedCardScore(int redCardScore) {
        this.redCardScore = redCardScore;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

}
