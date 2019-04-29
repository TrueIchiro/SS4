package Beispiel_02.FileUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//returns count of lines, count of words and characters per file
public class LOC {

    //reads from the console
    public void readFile() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            readFile(reader.readLine());
        } catch (IOException e) {

        }
    }

    //reads a file
    public void readFile(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));
        int countedLines = 0;
        int countedWords = 0;
        int countedCharacters = 0;

        String[] words;

        //reads line by line
        while (scanner.hasNext()) {
            //send one line to next block
            String line = scanner.nextLine();
            //words get split by space
            words = line.split(" ");

            for (int i = 0; i < words.length; i++) {
                //gets the length from one word and adds it
                countedCharacters += words[i].length();
                countedWords++;
            }

            countedLines++;
        }

        scanner.close();

        printStatistics(filename, countedLines, countedWords, countedCharacters);
    }

    private void printStatistics(String filename, int lines, int words, int characters) {
        System.out.println("Statistics for " + filename);
        System.out.println("--------------------");
        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
        System.out.println("Characters: " + characters);
    }

}
