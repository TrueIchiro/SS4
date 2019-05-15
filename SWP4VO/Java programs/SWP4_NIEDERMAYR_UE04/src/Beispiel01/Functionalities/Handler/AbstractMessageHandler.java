package Beispiel01.Functionalities.Handler;

import Beispiel01.Functionalities.SharedServerState;

public abstract class AbstractMessageHandler implements MessageHandler {

    protected SharedServerState sharedServerState;

    //constructor
    public AbstractMessageHandler(SharedServerState sharedServerState) {
        this.sharedServerState = sharedServerState;
    }

}
