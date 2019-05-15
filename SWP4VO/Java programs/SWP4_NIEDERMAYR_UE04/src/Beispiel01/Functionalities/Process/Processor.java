package Beispiel01.Functionalities.Process;

import Beispiel01.Functionalities.Handler.MessageHandler;

public interface Processor extends Runnable {

    void addHandler(MessageHandler handler);
    void removeHandler(MessageHandler handler);

}
