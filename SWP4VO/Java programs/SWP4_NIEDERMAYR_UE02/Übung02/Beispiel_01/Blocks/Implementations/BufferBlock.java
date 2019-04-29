package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.IntermediateBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//takes bufferSize of LINES and throws them at the next block
public class BufferBlock extends IntermediateBlock implements Observer, Observable {

    //bufferSize is per default 10
    private int bufferSize = 10;
    //savedValues is a list of Objects (gets Strings)
    private List<Object> savedValues = new ArrayList<>();

    private Set<Observer> registeredObservers = new HashSet<>();

    //constructor for creating the bufferBlock
    public BufferBlock(int bufferSize) {
        this.bufferSize = bufferSize;
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

    //functionality from IntermediateBlock
    //gets a line and saves it, until the count reaches bufferSize
    @Override
    public void process_stuff(Object o) throws Exception {
        //count of elements is still smaller than bufferSize
        if (this.savedValues.size() < this.bufferSize) {
            this.savedValues.add(o);
        } else { //the bufferSize is reached
            //throw the list of elements to the registered Observer
            notifyObservers(savedValues);
            //clear the list
            savedValues.clear();
        }
    }
}
