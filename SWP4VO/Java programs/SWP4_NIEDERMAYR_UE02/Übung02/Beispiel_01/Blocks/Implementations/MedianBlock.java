package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.IntermediateBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

import java.util.*;

//gets buffered values and calculates median
public class MedianBlock extends IntermediateBlock implements Observable, Observer {

    //list of observers
    private Set<Observer> registeredObservers = new HashSet<>();

    //size of window, default 3
    private int windowSize = 3;

    public MedianBlock(int windowSize) {
        this.windowSize = windowSize;
    }

    //funtionality from IntermediateBlock
    @Override
    public void process_stuff(Object o) throws Exception {
        //split string and turn to double
        Double[] doubles = splitAndTurn(o);
        //window with the values
        Double[] window = new Double[windowSize];
        //median will save all the medians
        Double[] median = new Double[doubles.length];

        int lower = 0;
        int upper = windowSize - 1;
        int edge = (int)(windowSize / 2);

        //goes through the array of doubles and calculates the median
        for (int i = 0; i < doubles.length; i++) {
            //do not change lower/upper if at the edge of array
            //e.g. windowSize = 3; (int)3/2 -> 1 > 0(do not change lower or upper)
            if ((i + edge) < doubles.length - 1 && (i - edge) > 0) {
                lower++;
                upper++;
            }

            window = createSortedWindow(doubles, lower, upper);
            median[i] = calculateMedian(window);
        }

        List<Object> values = new ArrayList<>();

        for (int i = 0; i < median.length; i++) {
            values.add(median[i]);
        }

        //now we have a Double[] with the median
        //and now THROW IT AT THE NEXT BLOCK LIKE U DON'T CARE
        //print(median);
        notifyObservers(values);
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

    @Override
    protected Double[] splitAndTurn(Object o) {
        //gets a line (string)
        //assumption: the numbers are separated by ,
        String s = String.valueOf(o);
        //o.toString();
        String[] arr = s.split(",");
        Double[] doubles = new Double[arr.length / 3];

        int pos = 0;

        //take three values and throw them into a container
        for (int j = 0; j < arr.length; j += 3) {
            arr[j] = arr[j].replace("[", "");
            arr[j] = arr[j].replace("]", "");

            doubles[pos] = Double.parseDouble(arr[j]);
            pos++;
        }

        return doubles;
    }

    private Double[] createSortedWindow(Double[] doubles, int lower, int upper) {
        Double[] sortedWindow = new Double[windowSize];

        int count = lower;
        int pos = 0;

        //add the needed values to the array
        while (count <= upper) {
            sortedWindow[pos] = doubles[count];

            pos++;
            count++;
        }

        //sort the values
        Arrays.sort(sortedWindow);

        return sortedWindow;
    }

    private double calculateMedian(Double[] sortedWindow) {
        double median = 0;

        if (windowSize % 2 == 0) { //it's even
            //median is the two values in the middle / 2
            median = (sortedWindow[windowSize/2 - 1] + sortedWindow[windowSize/2]) / 2;
        } else { //it's odd
            //median is the middle value
            //double is cast to int, anything after the decimal point is removed
            median = sortedWindow[(int)(windowSize/2)];
        }

        return median;
    }

}
