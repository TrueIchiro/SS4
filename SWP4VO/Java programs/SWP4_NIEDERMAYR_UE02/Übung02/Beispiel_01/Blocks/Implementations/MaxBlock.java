package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.IntermediateBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.util.HashSet;
import java.util.Set;

public class MaxBlock extends IntermediateBlock implements Observer, Observable {

    //list of observers
    private Set<Observer> registeredObservers = new HashSet<>();

    @Override
    public void process_stuff(Object o) throws Exception {
        Double[] doubles = splitAndTurn(o);
        double maxVal = 0;

        for (int i = 0; i < doubles.length; i++) {
            if (doubles[i] > maxVal) {
                maxVal = doubles[i];
            }
        }

        notifyObservers(maxVal);
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
        //store the object
        process_stuff(o);
    }
}
