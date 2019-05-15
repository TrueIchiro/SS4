package Beispiel01.Functionalities.Process.Implementation;

import Beispiel01.Functionalities.Handler.MessageHandler;
import Beispiel01.Functionalities.Message.Message;
import Beispiel01.Functionalities.Process.Processor;
import Beispiel01.Functionalities.SharedServerState;

import java.util.ArrayList;
import java.util.List;

public class MessageProcessorImplementation implements Processor {

    private SharedServerState sharedServerState;
    private List<MessageHandler> handlerList = new ArrayList<>();

    //constructor
    public MessageProcessorImplementation(SharedServerState sharedServerState) {
        this.sharedServerState = sharedServerState;
    }

    @Override
    public void addHandler(MessageHandler handler) {
        this.handlerList.add(handler);
    }

    @Override
    public void removeHandler(MessageHandler handler) {
        this.removeHandler(handler);
    }

    @Override
    public void run() {
        Message message;

        while ((message = sharedServerState.getReceiveMessageBuffer().get()) != null) {
            sharedServerState.getReceiveMessageBuffer().delete(message);
            System.out.println("Processor received " + message);

            for (MessageHandler handler : handlerList) {

                if (handler.handleMessage(message)) {
                    System.out.println("Handler " + handler
                            + " handled message: " + message);
                }

            }

        }

    }

}
