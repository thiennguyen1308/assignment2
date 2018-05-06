package com.faber;

/**
 *
 * @author THIEN
 */
public class Game {

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
        int team2YellowCard = randomGoalsGenerator.randomYellowCard();
        int team2RedCard = randomGoalsGenerator.randomRedCard();

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
        player1Team1.setGoals(player1Team1.getGoals() + randomGoalsGenerator.randomGoalInRange(0, team1Goal + 1));
        player2Team1.setGoals(player2Team1.getGoals() + team1Goal - player1Team1.getGoals());

        Player player1Team2 = team2.getPlayer1();
        Player player2Team2 = team2.getPlayer2();
        player1Team2.setGoals(player1Team2.getGoals() + randomGoalsGenerator.randomGoalInRange(0, team2Goal + 1));
        player2Team2.setGoals(player2Team2.getGoals() + team2Goal - player1Team2.getGoals());

        if (team1Goal > team2Goal) {
            team1.setPoint(3);
            team1.setWon(team1.getWon() + 1);
            team2.setPoint(0);
            team2.setLost(team2.getLost() + 1);
        } else if (team1Goal < team2Goal) {
            team1.setPoint(0);
            team1.setLost(team1.getLost() + 1);
            team2.setPoint(3);
            team2.setWon(team2.getWon() + 1);
        } else {
            if (isFinal) {
                if (isTeam1Win) {
                    team1.setPoint(3);
                    team1.setWon(team1.getWon() + 1);
                    team2.setPoint(0);
                    team2.setLost(team2.getLost() + 1);
                } else {
                    team1.setPoint(0);
                    team1.setLost(team1.getLost() + 1);
                    team2.setPoint(3);
                    team2.setWon(team2.getWon() + 1);
                }
            } else {
                team1.setPoint(1);
                team2.setPoint(1);
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
}
