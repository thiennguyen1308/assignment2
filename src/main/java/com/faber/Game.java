package com.faber;

import java.util.List;

/**
 *
 * @author THIEN
 */
public class Game {

    private List<Team> listTeam;
    private String championTeam = "";
    private String goldenBootAward = "";
    private String fairTeam = "";

    public Game() {

    }

    public Game(List<Team> listTeamÏ) {
        this.listTeam = listTeam;
    }

    public List<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(List<Team> listTeam) {
        this.listTeam = listTeam;
    }

    public String getChampionTeam() {
        return championTeam;
    }

    public void setChampionTeam(String winnerTeam) {
        this.championTeam = winnerTeam;
    }

    public String getGoldenBootAward() {
        return goldenBootAward;
    }

    public void setGoldenBootAward(String GoldenBootAward) {
        this.goldenBootAward = GoldenBootAward;
    }

    public String getFairTeam() {
        return fairTeam;
    }

    public void setFairTeam(String fairTeam) {
        this.fairTeam = fairTeam;
    }

    public void playGame(Team team1, Team team2, Boolean isFinal) {
        RandomGoalsGenerator randomGoalsGenerator = new RandomGoalsGenerator();

        int team1Goal;
        int team2Goal;
        boolean isTeam1Win = false;
        if (team1.getRanking() > team2.getRanking()) {
            team1Goal = randomGoalsGenerator.randomGoalInRange(0, 7);
            team2Goal = randomGoalsGenerator.randomGoalInRange(0, 7 - (team1.getRanking() - team2.getRanking()));
        } else {
            team1Goal = randomGoalsGenerator.randomGoalInRange(0, 7 - (team1.getRanking() - team2.getRanking()));
            team2Goal = randomGoalsGenerator.randomGoalInRange(0, 7);
        }
        int team1YellowCard = randomGoalsGenerator.randomYellowCard();
        int team1RedCard = randomGoalsGenerator.randomRedCard();
        team1.setYellowCardScore(team1.getYellowCardScore() + team1YellowCard);
        team1.setRedCardScore(team1.getRedCardScore() + team1RedCard);

        int team2YellowCard = randomGoalsGenerator.randomYellowCard();
        int team2RedCard = randomGoalsGenerator.randomRedCard();

        team2.setYellowCardScore(team2.getYellowCardScore() + team2YellowCard);
        team2.setRedCardScore(team2.getRedCardScore() + team2RedCard);

        if (isFinal && (team1Goal == team2Goal)) {
            isTeam1Win = playPenaltyShootOut(team1, team2);
        }
        displayGameResult(team1, team2, team1Goal, team2Goal, team1YellowCard, team2YellowCard, team1RedCard, team2RedCard);
        team1.setPlayed(team1.getPlayed() + 1);
        team2.setPlayed(team2.getPlayed() + 1);
        team1.setGoal(team1.getGoal() + team1Goal);
        team2.setGoal(team2.getGoal() + team2Goal);

        Player player1Team1 = team1.getPlayer1();
        Player player2Team1 = team1.getPlayer2();
        int player1Goals = randomGoalsGenerator.randomGoalInRange(0, team1Goal);
        player1Team1.setGoals(player1Team1.getGoals() + player1Goals);
        player2Team1.setGoals(player2Team1.getGoals() + team1Goal - player1Goals);

        Player player1Team2 = team2.getPlayer1();
        Player player2Team2 = team2.getPlayer2();
        int player2Goals = randomGoalsGenerator.randomGoalInRange(0, team2Goal);
        player1Team2.setGoals(player1Team2.getGoals() + player2Goals);
        player2Team2.setGoals(player2Team2.getGoals() + team2Goal - player2Goals);

        if (team1Goal > team2Goal) {
            championTeam = team1.getName();
            team1.setPoint(team1.getPoint() + 3);
            team1.setWon(team1.getWon() + 1);
            team2.setLost(team2.getLost() + 1);
        } else if (team1Goal < team2Goal) {
            championTeam = team2.getName();
            team1.setLost(team1.getLost() + 1);
            team2.setPoint(team2.getPoint() + 3);
            team2.setWon(team2.getWon() + 1);
        } else {
            if (isFinal) {
                if (isTeam1Win) {
                    championTeam = team1.getName();
                    team1.setPoint(team1.getPoint() + 3);
                    team1.setWon(team1.getWon() + 1);
                    team2.setLost(team2.getLost() + 1);
                } else {
                    championTeam = team2.getName();
                    team1.setLost(team1.getLost() + 1);
                    team2.setPoint(3);
                    team2.setWon(team2.getWon() + 1);
                }
            } else {
                team1.setPoint(team1.getPoint() + 1);
                team2.setPoint(team2.getPoint() + 1);
                team1.setDrawn(team1.getDrawn() + 1);
                team2.setDrawn(team2.getDrawn() + 1);
            }
        }

    }

    public boolean playPenaltyShootOut(Team team1, Team team2) {
        RandomGoalsGenerator randomGoalsGenerator = new RandomGoalsGenerator();

        int shots = 0;
        int team1Goal = 0;
        int team2Goal = 0;
        while (shots < 6) {
            team1Goal += randomGoalsGenerator.randomGoal();
            team2Goal += randomGoalsGenerator.randomGoal();
            if (shots == 5 && (team1Goal == team2Goal)) {
                shots = 0;
                team1Goal = 0;
                team2Goal = 0;
            } else {
                shots++;
            }
        }
        System.out.println("penalty shoot out result: " + team1.getName() + " " + team1Goal + " " + team2.getName() + " " + team2Goal);
        return team1Goal > team2Goal;
    }

    public void displayGameResult(Team team1, Team team2, int team1Goal, int team2Goal, int team1YellowCard, int team2YellowCard, int team1RedCard, int team2RedCard) {
        System.out.println("\nGame result: " + team1.getName() + " " + team1Goal + " vs. " + team2.getName() + " " + team2Goal);
        if (team1YellowCard != 0 || team1RedCard != 0) {
            System.out.println(team1.getName() + " - " + (team1YellowCard == 1 ? "1 yellow card" : "") + (team1RedCard == 1 ? " - 1 red card" : ""));
        }
        if (team2YellowCard != 0 || team2RedCard != 0) {
            System.out.println(team2.getName() + " - " + (team2YellowCard == 1 ? "1 yellow card" : "") + (team2RedCard == 1 ? " - 1 red card" : ""));
        }
    }

    public void viewStatistic() {
        int mostGoal = 0;
        int mostCardScore = 100;
        for (int i = 0; i < listTeam.size(); i++) {
            if (listTeam.get(i).getPlayer1().getGoals() > mostGoal) {
                mostGoal = listTeam.get(i).getPlayer1().getGoals();
                goldenBootAward = listTeam.get(i).getPlayer1().getName() + " from " + listTeam.get(i).getName();
            } else if (listTeam.get(i).getPlayer1().getGoals() == mostGoal) {
                mostGoal = listTeam.get(i).getPlayer1().getGoals();
                goldenBootAward = ", " + listTeam.get(i).getPlayer1().getName() + " from " + listTeam.get(i).getName();
            }
            if (listTeam.get(i).getPlayer2().getGoals() > mostGoal) {
                mostGoal = listTeam.get(i).getPlayer2().getGoals();
                goldenBootAward = listTeam.get(i).getPlayer2().getName() + " from " + listTeam.get(i).getName();
            } else if (listTeam.get(i).getPlayer2().getGoals() == mostGoal) {
                mostGoal = listTeam.get(i).getPlayer2().getGoals();
                goldenBootAward = ", " + listTeam.get(i).getPlayer2().getName() + " from " + listTeam.get(i).getName();
            }
            int cardScore = listTeam.get(i).getYellowCardScore() + (2 * listTeam.get(i).getRedCardScore());
            if (cardScore < mostCardScore) {
                mostCardScore = cardScore;
                fairTeam = listTeam.get(i).getName();
            } else if (cardScore == mostCardScore) {
                fairTeam += " and " + listTeam.get(i).getName();
            }
        }

    }
}
