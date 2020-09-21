package com.company;

import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("Insert search word: ");
        Scanner inputSc = new Scanner(System.in); //Läser av sökord och sparar det i en String vi kallar input.
        String input = inputSc.nextLine();
        File startingFile = new File("C:\\Users\\Zacharias Göransson\\Documents\\File-Crawler\\testDataLabb"); //Mapp var programmet ska börja söka. (borde ändras)
        goThroughAllFoldersAndFiles(input, startingFile); //Kallar på nästa metod
    }

    public static void goThroughAllFoldersAndFiles(String input, File file) { //Metod som går igenom alla mappar och söker filer
        if (file.isFile()) { //Om det är en fil: Fortsätt programmet.
            printPathIfWordIsInFile(input, file);

        } else if (file.isDirectory()){ //Om det är en map: kör samma metod igen (rekursion).
                try {
                    File[] folderContents = file.listFiles();
                    for (int i = 0; i < folderContents.length; i++) {
                        File f = folderContents[i]; //Bestämmer vad i listan vi vill titta på.
                        goThroughAllFoldersAndFiles(input, f); //Här sker rekursionen.
                    }
                } catch (Exception e) {
                    System.err.println(e + "The file: " + file.getAbsolutePath() + "could not be read");
                }
            }
    }

    public static void printPathIfWordIsInFile(String input, File file) { //Metod som letar efter det sökta ordet i filen
        try {
            Scanner fileSc = new Scanner(file);
            while(fileSc.hasNextLine()){ //Går igenom rad för rad
                if (fileSc.nextLine().contains(input)){ //Skriver ut path till filen om den innehåller ordet
                    System.out.println("The file containing the word is here: " + file.getCanonicalPath());
                }
            }
        } catch (Exception e) {
            System.err.println(e + "Error");
        }
    }
}

