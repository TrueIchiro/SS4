package Beispiel01.Functionalities.Send.Implementation;

import Beispiel01.Functionalities.Message.Message;
import Beispiel01.Functionalities.Send.MessageSender;
import Beispiel01.Functionalities.SharedServerState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class MessageSenderImplementation implements MessageSender {

    private SharedServerState sharedServerState;

    //constructor
    public MessageSenderImplementation(SharedServerState sharedServerState) {
        this.sharedServerState = sharedServerState;
    }

    @Override
    public void run() {
        Message message;

        while ((message = sharedServerState.getSendMessageBuffer().get()) != null) {

            try {
                sharedServerState.getSendMessageBuffer().delete(message);
                byte[] rawMessage =
                        (message.getCommand() + message.getPayload()).getBytes();
                DatagramPacket packet =
                        new DatagramPacket(
                                rawMessage,
                                rawMessage.length,
                                InetAddress.getByName(message.getHost()),
                                message.getPort()
                        );
                sharedServerState.getServerSocket().send(packet);
                System.out.println("Message send to client " + message);
            } catch (IOException e) {
                System.err.println("Failed to send message " + message);
            }
        }
    }

}
