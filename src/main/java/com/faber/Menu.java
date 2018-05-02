package com.faber;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author THIEN
 */
public class Menu {

    public Menu() {
    }

    private List<Team> listTeam = new ArrayList<>();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.readFile();
        Scanner sc = new Scanner(System.in);
        menuLoop:
        while (true) {
            System.out.println("A. Play Preliminary Stage \n"
                    + "B. Play Final \n"
                    + "C. Display Teams \n"
                    + "D. Display Players \n"
                    + "E. Display Cup Result \n"
                    + "X. Close \n"
            );
            String option = sc.nextLine();
            switch (option) {
                case "A":
                case "B":
                case "C":
                case "D":
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
            fr = new FileReader("C:/Users/THIEN/Downloads/teams.txt");
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
}
