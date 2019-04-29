package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.TerminalBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.io.FileWriter;

//gets a signal from DifferentialBlock
public class FileWriterBlock extends TerminalBlock implements Observer {

    //per default output.csv
    private String filename = "output.csv";

    public FileWriterBlock(String filename) {
        this.filename = filename;
    }

    @Override
    public void show_data(Observable source, Object o) throws Exception {
        String[] strings = splitAndTurnToString(o);

        FileWriter writer = new FileWriter(this.filename, true);

        for (int i = 0; i < strings.length; i++) {
            writer.write(strings[i]);

            writer.write("\n");
        }

        writer.close();
    }

    @Override
    public void update(Observable source, Object o) throws Exception {
        show_data(source, o);
    }

    //also has this cool function I totally did not steal
    protected String[] splitAndTurnToString(Object o) throws Exception {
        //gets a line (string)
        //assumption: the numbers are separated by ,
        String s = String.valueOf(o);
        //o.toString();
        String[] arr = s.split(",");

        //take three values and throw them into a container
        for (int j = 0; j < arr.length; j++) {
            arr[j] = arr[j].replace("[", "");
            arr[j] = arr[j].replace("]", "");
            arr[j] = arr[j].replace(" ", "");
        }

        return arr;
    }
}
