package com.faber;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//</editor-fold>
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

    public Game(List<Team> listTeam) {
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
    
    //<editor-fold defaultstate="collapsed" desc="INPUR PLAYER NAME">
    public void inputPlayerName() {
        Player player = new Player();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < listTeam.size(); i++) {
            Boolean player1SecondTry = false;
            Player player1 = new Player();
            String player1Name;
            while (true) {
                System.out.println("Please enter name for " + listTeam.get(i).getName() + " player 1");
                player1Name = sc.nextLine();

                if (!player.isValidateName(player1Name)) {
                    if (player1SecondTry) {
                        player1Name = "player-1-" + listTeam.get(i).getName();
                    } else {
                        player1SecondTry = true;
                        continue;
                    }
                }
                System.out.println("1st player in " + listTeam.get(i).getName() + " team is " + player1Name);
                player1.setName(player1Name);
                break;
            }
            listTeam.get(i).setPlayer1(player1);

            Boolean player2SecondTry = false;
            Player player2 = new Player();
            String player2Name;
            while (true) {
                System.out.println("Please enter name for " + listTeam.get(i).getName() + " player 2");
                player2Name = sc.nextLine();

                if (!player.isValidateName(player2Name) || player2Name.equals(player1Name)) {
                    if (player2SecondTry) {
                        player2Name = "player-2-" + listTeam.get(i).getName();
                    } else {
                        player2SecondTry = true;
                        continue;
                    }
                }
                System.out.println("2nd player in " + listTeam.get(i).getName() + " team is " + player2Name);
                player2.setName(player2Name);
                break;
            }
            listTeam.get(i).setPlayer2(player2);
        }
    }
    //</editor-fold>

    public void playGame(int team1Index, int team2Index, Boolean isFinal) {
        Team team1 = listTeam.get(team1Index);
        Team team2 = listTeam.get(team2Index);
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
        displayGameResult(team1, team2, team1Goal, team2Goal, team1YellowCard, team2YellowCard, team1RedCard, team2RedCard);//view game result after finish rand goal and score.
        
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

    public void viewTeam() {
        System.out.println("\n######################################################Team fixture##########################################");
        System.out.format("%-15s%-10s%-10s%-10s%-10s%-10s%-10s%-20s\n", new String[]{"", "Played", "Won", "Lost", "Drawn", "Goals", "Points", "Fair Play Score"});
        for (Team team : listTeam) {
            System.out.format("%-18s%-8s%-10s%-10s%-10s%-11s%-15s%-20s\n", new String[]{team.getName(), team.getPlayed() + "", team.getWon() + "", team.getLost() + "", team.getDrawn() + "", team.getGoal() + "", team.getPoint() + "", (team.getYellowCardScore() + 2 * team.getRedCardScore()) + ""});
        }
        System.out.println("");
    }

    public void viewPlayer() {
        for (Team team : listTeam) {
            Player player1 = team.getPlayer1();
            Player player2 = team.getPlayer2();
            System.out.println(player1.getName() + " (" + team.getName() + ") - " + player1.getGoals());
            System.out.println(player2.getName() + " (" + team.getName() + ") - " + player2.getGoals());
        }
    }

    public void sortTeam() {
        for (int i = 0; i < listTeam.size(); i++) {
            for (int j = 1; j < listTeam.size(); j++) {
                if (listTeam.get(j).getPoint() > listTeam.get(j - 1).getPoint()) {
                    Collections.swap(listTeam, j, j - 1);
                    continue;
                }
                if (listTeam.get(j).getPoint() == listTeam.get(j - 1).getPoint()) {
                    if (listTeam.get(j).getGoal() > listTeam.get(j - 1).getGoal()) {
                        Collections.swap(listTeam, j, j - 1);
                        continue;
                    }
                    if (listTeam.get(j).getGoal() == listTeam.get(j - 1).getGoal()) {
                        Random rand = new Random();
                        if (rand.nextBoolean()) {
                            Collections.swap(listTeam, j, j - 1);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < listTeam.size(); i++) {
            listTeam.get(i).setRanking(i + 1);
        }
    }

    public void makeStatistic() {
        int mostGoal = 0;
        int mostCardScore = 100;
        for (int i = 0; i < listTeam.size(); i++) {
            // get player1 most scored
            if (listTeam.get(i).getPlayer1().getGoals() > mostGoal) {
                mostGoal = listTeam.get(i).getPlayer1().getGoals();
                goldenBootAward = listTeam.get(i).getPlayer1().getName() + " from " + listTeam.get(i).getName();
            } else if (listTeam.get(i).getPlayer1().getGoals() == mostGoal) {
                mostGoal = listTeam.get(i).getPlayer1().getGoals();
                goldenBootAward = ", " + listTeam.get(i).getPlayer1().getName() + " from " + listTeam.get(i).getName();
            }
            // get player2 most scored and compare with player1
            if (listTeam.get(i).getPlayer2().getGoals() > mostGoal) {
                mostGoal = listTeam.get(i).getPlayer2().getGoals();
                goldenBootAward = listTeam.get(i).getPlayer2().getName() + " from " + listTeam.get(i).getName();
            } else if (listTeam.get(i).getPlayer2().getGoals() == mostGoal) {
                mostGoal = listTeam.get(i).getPlayer2().getGoals();
                goldenBootAward = ", " + listTeam.get(i).getPlayer2().getName() + " from " + listTeam.get(i).getName();
            }
            int cardScore = listTeam.get(i).getYellowCardScore() + (2 * listTeam.get(i).getRedCardScore());// get card score current team
            if (cardScore < mostCardScore) {//return team has the least card score
                mostCardScore = cardScore;
                fairTeam = listTeam.get(i).getName();
            } else if (cardScore == mostCardScore) {
                fairTeam += " and " + listTeam.get(i).getName();
            }
        }

    }

    public void viewStatistic() {
        System.out.println("Football World Cup Winner: " + championTeam);
        System.out.println("golden boot award: " + goldenBootAward);
        System.out.println("fair team: " + fairTeam);
    }

    public void writeStatisticFile() {
        PrintWriter writer = null;// init write file
        try {
            writer = new PrintWriter("/Users/nguyenthong/Downloads/statistic.txt", "UTF-8"); //open file with utf-8 format
            writer.println("Football World Cup Winner: " + championTeam);//write file
            writer.println("golden boot award: " + goldenBootAward);//write file
            writer.println("fair team: " + fairTeam);//write file
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
        } finally {
            writer.close();
        }
    }

}
