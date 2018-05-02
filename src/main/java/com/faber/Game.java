package com.faber;

/**
 *
 * @author THIEN
 */
public class Game {

    public void playGame(Team team1, Team team2) {
        int team1Goal;
        int team2Goal;
        if (team1.getRanking() > team2.getRanking()) {
            team1Goal = RandomGoalsGenerator.randomGoalInRange(0, 7);
            team2Goal = RandomGoalsGenerator.randomGoalInRange(0, 7) - (team1.getRanking() - team2.getRanking());
        } else {
            team1Goal = RandomGoalsGenerator.randomGoalInRange(0, 7) - (team1.getRanking() - team2.getRanking());
            team2Goal = RandomGoalsGenerator.randomGoalInRange(0, 7);
        }

        team1.setGoal(team1.getGoal() + team1Goal);
        team2.setGoal(team2.getGoal() + team2Goal);

        Player player1Team1 = team1.getPlayer1();
        Player player2Team1 = team1.getPlayer2();
        player1Team1.setGoals(player1Team1.getGoals() + RandomGoalsGenerator.randomGoalInRange(0, team1Goal));
        player2Team1.setGoals(team1Goal - player1Team1.getGoals());

        Player player1Team2 = team2.getPlayer1();
        Player player2Team2 = team2.getPlayer2();
        player1Team2.setGoals(player1Team2.getGoals() + RandomGoalsGenerator.randomGoalInRange(0, team2Goal));
        player2Team2.setGoals(team2Goal - player1Team2.getGoals());

        if (team1Goal > team2Goal) {
            team1.setPoint(3);
            team1.setWon(team1.getGoal() + 1);
            team2.setPoint(0);
            team2.setLost(team2.getLost() + 1);
        } else if (team1Goal < team2Goal) {
            team1.setPoint(0);
            team1.setLost(team1.getLost() + 1);
            team2.setPoint(3);
            team2.setWon(team2.getGoal() + 1);
        } else {
            team1.setPoint(1);
            team2.setPoint(1);
            team1.setLost(team1.getDrawn() + 1);
            team2.setLost(team2.getDrawn() + 1);
        }
    }

    public void playPenaltyShootOut() {
    }

    public void displayGameResult(Team team1, Team team2, int team1Goal, int team2Goal) {
        System.out.println("Game result: " + team1.getName() + " " + team1Goal + " vs. " + team2.getName() + " " + team2Goal);
    }
}
