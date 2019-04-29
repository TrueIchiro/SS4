package Beispiel_02.FileUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//takes the first x lines of a file
public class Head {

    //how many lines should be copied
    int lineCount = 0;

    public Head(int lineCount) {
        this.lineCount = lineCount;
    }

    public Head() {
        this.lineCount = 9000;
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
            int countedLines = 0;

            //reads line by line
            while (scanner.hasNext() && countedLines < this.lineCount) {
                //send one line to next block
                String line = scanner.nextLine();

                if (!line.equals("") || line.equals("\n")) {
                    System.out.println(line);
                    countedLines++;
                }
            }

            scanner.close();
        } else {
            System.out.println("You can't return a negative amount of lines!");
        }
    }

}
