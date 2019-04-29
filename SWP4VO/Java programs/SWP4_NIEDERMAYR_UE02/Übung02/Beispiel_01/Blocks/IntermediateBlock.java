package Beispiel_01.Blocks;

import Beispiel_01.Observer;

public abstract class IntermediateBlock implements Block {

    public abstract void process_stuff(Object o) throws Exception;

    protected Double[] splitAndTurn(Object o) {
        //gets a line (string)
        //assumption: the numbers are separated by ,
        String s = String.valueOf(o);
        //o.toString();
        String[] arr = s.split(",");
        Double[] doubles = new Double[arr.length];

        //take three values and throw them into a container
        for (int j = 0; j < arr.length; j++) {
            arr[j] = arr[j].replace("[", "");
            arr[j] = arr[j].replace("]", "");

            doubles[j] = Double.parseDouble(arr[j]);
        }

        return doubles;
    }

}
