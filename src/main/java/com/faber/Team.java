package com.faber;

/**
 *
 * @author THIEN
 */
public class Team {

    private String name;
    private int ranking;
    private int point;
    private int goal;
    private int won;
    private int lost;
    private int drawn;
    private int yellowCardScore;
    private int redCardScore;
    private Player player1;
    private Player player2;

    public Team() {
    }

    public Team(String name, int ranking, int point, int goal, int won, int lost, int drawn, int yellowCardScore, int redCardScore, Player player1, Player player2) {
        this.name = name;
        this.ranking = ranking;
        this.point = point;
        this.goal = goal;
        this.won = won;
        this.lost = lost;
        this.drawn = drawn;
        this.yellowCardScore = yellowCardScore;
        this.redCardScore = redCardScore;
        this.player1 = player1;
        this.player2 = player2;
    }


    public Team(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
        this.point = 0;
        this.goal = 0;
        this.won = 0;
        this.lost = 0;
        this.drawn = 0;
        this.redCardScore = 0;
        this.yellowCardScore = 0;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
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
