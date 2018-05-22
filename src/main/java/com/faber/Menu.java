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
        game.inputPlayerName();
        Scanner sc = new Scanner(System.in);
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
                        for (int i = 0; i < game.getListTeam().size(); i++) {
                            for (int j = 0; j < game.getListTeam().size(); j++) {
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
            fr = new FileReader("C:\\Users\\PC\\Downloads\\teams.txt");
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
}
