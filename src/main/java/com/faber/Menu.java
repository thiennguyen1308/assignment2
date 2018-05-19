package com.faber;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//</editor-fold>

/**
 *
 * @author THIEN
 */
public class Menu {

    private boolean isPlayedPreliminary = false;
    private boolean isPlayedFinal = false;

    public Menu() {
    }
    public static Game game = new Game();

    public void start() {
        
        //read input file
        readFile();

        //<editor-fold defaultstate="collapsed" desc="INPUR PLAYER NAME">
        Scanner sc = new Scanner(System.in);
        List<Team> listTeam = game.getListTeam();
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
        //</editor-fold>

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
                    if (!isPlayedPreliminary) {
                        for (int i = 0; i < listTeam.size(); i++) {
                            for (int j = 0; j < listTeam.size(); j++) {
                                if (j == i) {
                                    continue;
                                }
                                game.playGame(i, j, false);
                            }
                        }
                        game.sortTeam();
                        game.makeStatistic();
                        isPlayedPreliminary = true;
                    } else {
                        System.out.println("You've already played Preliminary Stage");
                    }
                    break;
                }
                case "B": {
                    if (isPlayedFinal) {
                        System.out.println("You've already played Final Stage");
                    }
                    if (isPlayedPreliminary) {
                        game.playGame(0, 1, true);
                        isPlayedFinal = true;
                        game.sortTeam();
                        game.makeStatistic();
                    } else {
                        System.out.println("Please play Preliminary stage before final");
                        break;
                    }
                    break;
                }
                case "C":
                    game.viewTeam();
                    break;
                case "D":
                    game.viewPlayer();
                    break;
                case "E":
                    if (isPlayedFinal) {
                        game.viewStatistic();
                    } else {
                        System.out.println("Please complete final stage before view statistic");
                    }
                    break;
                case "X": {
                    game.writeStatisticFile();
                    break menuLoop;
                }
                default: {
                    System.out.println("Please enter valid option below");
                    break;
                }
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="READ FILE">
    private void readFile() {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("/Users/nguyenthong/Downloads/teams.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;
            List<Team> listTeam = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String name = sCurrentLine.split(",")[0];
                int ranking = Integer.parseInt(sCurrentLine.split(",")[1]);
                listTeam.add(new Team(name, ranking));
            }
            game.setListTeam(listTeam);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CHECK VALIDATE TEAM">
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
    //</editor-fold>

}
