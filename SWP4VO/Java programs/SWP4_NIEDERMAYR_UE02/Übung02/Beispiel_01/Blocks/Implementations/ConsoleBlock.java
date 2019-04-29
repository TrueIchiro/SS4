package Beispiel_01.Blocks.Implementations;

import Beispiel_01.Blocks.TerminalBlock;
import Beispiel_01.Observable;
import Beispiel_01.Observer;

//only observer
//takes data and throws it at the console
public class ConsoleBlock extends TerminalBlock implements Observer {

    @Override
    public void show_data(Observable source, Object o) {
        System.out.println("Received value from " + source.toString() + ": " + o);
    }

    @Override
    public void update(Observable source, Object o) {
        show_data(source, o);
    }
}
