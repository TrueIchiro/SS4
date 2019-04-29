package Beispiel_02.FileUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tail {

    //how many lines should be copied
    int lineCount = 0;

    public Tail(int lineCount) {
        this.lineCount = lineCount;
    }

    public Tail() {
        //it's OVER 9000!!!
        this.lineCount = 9001;
    }

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
        if (this.lineCount > 0) {
            Scanner scanner = new Scanner(new File(filename));
            List<String> strings = new ArrayList<>();

            //reads line by line
            //has to read all before you can take the last few lines
            while (scanner.hasNext()) {
                strings.add(scanner.nextLine());
            }

            //if the count of the lines the user wants is smaller than the actual size of lines
            if (this.lineCount < strings.size()) {
                //takes strings.size - lineCount
                for (int i = (strings.size() - lineCount); i < strings.size(); i++) {
                    System.out.println(strings.get(i));
                }
            } else { //user wants more lines than there are
                for (int i = 0; i < strings.size(); i++) {
                    System.out.println(strings.get(i));
                }
            }

            scanner.close();
        } else {
            System.out.println("You can't return a negative amount of lines!");
        }
    }

}
