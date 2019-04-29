package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.Block;
import Beispiel_01.Blocks.EmitBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileReaderBlock extends EmitBlock implements Observable {

    public FileReaderBlock() {}

    private Set<Observer> registeredObservers = new HashSet<>();

    //reads filename from user input
    public void create_stream() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            create_stream(reader.readLine());
        } catch (IOException e) {

        }
    }

    //overloaded version of create_stream
    public void create_stream(String filename) throws Exception {
            Scanner scanner = new Scanner(new File(filename));

            //reads line by line
            while (scanner.hasNext()) {
                //send one line to next block
                String line = scanner.nextLine();
                //System.out.println(line);
                this.notifyObservers(line);
            }

            scanner.close();
    }

    @Override
    public void registerObserver(Observer observer) {
        if(observer != null) {
            this.registeredObservers.add(observer);
        }
    }

    @Override
    public void unregisterObserver(Observer observer) {
        if(observer != null) {
            this.registeredObservers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(Object o) throws Exception {
        for (Observer observer : registeredObservers) {
            observer.update(this, o);
        }
    }
}
