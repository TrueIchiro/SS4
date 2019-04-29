package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.IntermediateBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//uses differential methods
public class DifferentialBlock extends IntermediateBlock implements Observer, Observable {

    //list of observers
    private Set<Observer> registeredObservers = new HashSet<>();

    //functionality from IntermediateBlock
    @Override
    public void process_stuff(Object o) throws Exception {
        //gets a Double[] from MedianBlock
        Double[] doubles = splitAndTurn(o);
        Double[] diffs = differential(doubles);

        List<Object> values = new ArrayList<>();

        for (int i = 0; i < diffs.length; i++) {
            values.add(diffs[i]);
        }

        //System.out.println(o);

        //now throw it at the observers!
        //print(diffs);
        notifyObservers(values);
    }

    private Double[] differential(Double[] doubles) {
        Double[] diffs = new Double[doubles.length - 1];

        //one number is lost
        for (int i = 0; i < doubles.length - 1; i++) {
            diffs[i] = (doubles[i + 1] - doubles[i]);
        }

        return diffs;
    }

    //functionalities from Observable
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

    //functionality from Observer
    @Override
    public void update(Observable source, Object o) throws Exception {
        process_stuff(o);
    }
}
