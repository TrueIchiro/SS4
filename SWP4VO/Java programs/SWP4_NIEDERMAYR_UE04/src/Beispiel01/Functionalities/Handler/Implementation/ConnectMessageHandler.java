package Beispiel01.Functionalities.Handler.Implementation;

import Beispiel01.Functionalities.Client;
import Beispiel01.Functionalities.Handler.AbstractMessageHandler;
import Beispiel01.Functionalities.Message.Message;
import Beispiel01.Functionalities.SharedServerState;

public class ConnectMessageHandler extends AbstractMessageHandler {

    //can process messages with command c
    private static final char COMMAND = 'c';

    public ConnectMessageHandler(SharedServerState sharedServerState) {
        super(sharedServerState);
    }

    @Override
    public boolean handleMessage(Message message) {

        //TODO: Check if everything is in order
        if (COMMAND == message.getCommand()) {
            String[] data = message.getPayload().split(" ");
            Client client = new Client(
                    message.getHost(),
                    message.getPort(),
                    Integer.valueOf(data[0]),
                    data[1]
            );

            sharedServerState.addClient(client);

            //acknowledgement
            sharedServerState.getSendMessageBuffer().add(
                    new Message(
                            message.getHost(),
                            client.getReceivingPort(),
                            'o',
                            " " + message.getPort()
                    )
            );
            return true;
        }

        return false;
    }

}
