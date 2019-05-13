package Beispiel01.Functionalities.Receiver.Implementation;

import Beispiel01.Functionalities.Message.Message;
import Beispiel01.Functionalities.Receiver.MessageReceiver;
import Beispiel01.Functionalities.SharedServerState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageReceiverImplementation implements MessageReceiver {

    private SharedServerState sharedServerState;
    private AtomicBoolean stopRequest = new AtomicBoolean(false);

    public MessageReceiverImplementation(SharedServerState sharedServerState) {
        this.sharedServerState = sharedServerState;
    }

    @Override
    public void run() {
        //receive message from socket and create Message object
        //post it to MessageBuffer
        byte[] buffer = new byte[2048];
        DatagramPacket pkt = new DatagramPacket(buffer, buffer.length);

        while (!stopRequest.get()) {

            try {
                //receive blocks until buffer is full
                sharedServerState.getServerSocket().receive(pkt);
                String message = new String(buffer, 0, pkt.getLength());

                //c client1 9991
                pkt.setLength(buffer.length);

                //parse the message
                if (message.length() > 0) {
                    char command = message.charAt(0);

                    if (message.length() > 2) {
                        sharedServerState.getReceiveMessageBuffer()
                                .add(new Message(pkt.getAddress().getHostAddress(),
                                        pkt.getPort(), command, message.substring(2)));
                    }

                }

            } catch (IOException e) {
                System.err.println("Received an error processing the buffer!");
            }

        }

    }

}
