package com.faber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author THIEN
 */
public class Menu {

    public Menu() {
    }

    private static List<Team> listTeam = new ArrayList<>();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.readFile();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < listTeam.size(); i++) {
            Boolean player1SecondTry = false;
            Player player1 = new Player();
            String player1Name;
            while (true) {
                System.out.println("Please enter name for " + listTeam.get(i).getName() + " player 1");
                player1Name = sc.nextLine();

                if (!isValidateName(player1Name)) {
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

                if (!isValidateName(player2Name) || player2Name.equals(player1Name)) {
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
        Boolean isFinishPreliminaryStage = false;
        menuLoop:
        while (true) {
            System.out.println("################################################### Menu ###########################################################");
            System.out.println("A. Play Preliminary Stage \n"
                    + "B. Play Final \n"
                    + "C. Display Teams \n"
                    + "D. Display Players \n"
                    + "E. Display Cup Result \n"
                    + "X. Close \n"
            );
            String option = sc.nextLine();
            switch (option) {
                case "A": {
                    Game game = new Game();
                    for (int i = 0; i < listTeam.size(); i++) {
                        Team team1 = listTeam.get(i);
                        for (int j = i + 1; j < listTeam.size(); j++) {
                            Team team2 = listTeam.get(j);
                            game.playGame(team1, team2, false);
                        }
                    }
                    sortTeam(listTeam);
                    isFinishPreliminaryStage = true;
                    break;
                }
                case "B": {
                    if (isFinishPreliminaryStage) {

                    } else {
                        System.out.println("Please play Preliminary stage before final");
                        break;
                    }
                }
                case "C":
                    viewTeam(listTeam);
                    break;
                case "D":
                    viewPlayer(listTeam);
                    break;
                case "E":
                case "X": {
                    break menuLoop;
                }
                default: {
                    System.out.println("Please enter valid option below");
                    break;
                }
            }
        }
    }

    private void readFile() {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("C:\\Users\\PC\\Downloads\\teams.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String name = sCurrentLine.split(",")[0];
                int ranking = Integer.parseInt(sCurrentLine.split(",")[1]);
                listTeam.add(new Team(name, ranking));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    public static boolean isValidateName(String name) {
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

    public static void viewTeam(List<Team> listTeam) {
        System.out.println("\n######################################################Team fixture##########################################");
        System.out.format("%-15s%-10s%-10s%-10s%-10s%-10s%-10s%-20s\n", new String[]{"", "Played", "Won", "Lost", "Drawn", "Goals", "Points", "Fair Play Score"});
        for (Team team : listTeam) {
            System.out.format("%-18s%-8s%-10s%-10s%-10s%-11s%-15s%-20s\n", new String[]{team.getName(), team.getPlayed() + "", team.getWon() + "", team.getLost() + "", team.getDrawn() + "", team.getGoal() + "", team.getPoint() + "", (team.getYellowCardScore() + 2 * team.getRedCardScore()) + ""});
        }
        System.out.println("");
    }

    public static void sortTeam(List<Team> listTeam) {
        viewTeam(listTeam);

        for (int i = 0; i < listTeam.size(); i++) {
//            System.out.println(listTeam.get(i).getName());
            for (int j = i + 1; j < listTeam.size(); j++) {
                Team team = listTeam.get(j - 1);
                if (listTeam.get(j).getPoint() > team.getPoint()) {
                    Collections.swap(listTeam, j, j - 1);
                } else if (listTeam.get(j).getPoint() == team.getPoint()) {
                    if (listTeam.get(j).getGoal() > team.getGoal()) {
                        Collections.swap(listTeam, j, j - 1);
                    } else if (listTeam.get(j).getGoal() == team.getGoal()) {
//                        Random rand = new Random();
//                        if (rand.nextBoolean()) {
//                            Collections.swap(listTeam, j, j - 1);
//                        }
                    }
                }
            }

        }
    }

    public static void viewPlayer(List<Team> listTeam) {
        for (Team team : listTeam) {
            Player player1 = team.getPlayer1();
            Player player2 = team.getPlayer2();
            System.out.println(player1.getName() + " (" + team.getName() + ") - " + player1.getGoals());
            System.out.println(player2.getName() + " (" + team.getName() + ") - " + player2.getGoals());
        }
    }
}
