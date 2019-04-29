package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.IntermediateBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.util.HashSet;
import java.util.Set;

//is both Observer and Observable
//gets stuff from FileReaderBlock and throws it to ConsoleBlock
public class SumBlock extends IntermediateBlock implements Observable, Observer {

    private Set<Observer> registeredObservers = new HashSet<>();

    @Override
    public void process_stuff(Object o) throws Exception {
        //gets a line (string)
        //assumption: the numbers are separated by ,
        //can also be ;
        String s = o.toString();
        String[] arr = s.split(",");
        double sum = 0;

        for (int j = 0; j < arr.length; j++) {
            sum += Double.parseDouble(arr[j]);
        }
        //also notify the observers after processing
        notifyObservers(sum);
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

    @Override
    public void update(Observable source, Object o) throws Exception {
        //process stuff
        process_stuff(o);
    }
}
