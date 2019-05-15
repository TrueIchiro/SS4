package Beispiel01.Functionalities;

import Beispiel01.Functionalities.Buffer.MessageBuffer;
import Beispiel01.Functionalities.Handler.Implementation.ConnectMessageHandler;
import Beispiel01.Functionalities.Message.Implementation.MessageBufferImplementation;
import Beispiel01.Functionalities.Process.Implementation.MessageProcessorImplementation;
import Beispiel01.Functionalities.Process.Processor;
import Beispiel01.Functionalities.Receiver.Implementation.MessageReceiverImplementation;
import Beispiel01.Functionalities.Receiver.MessageReceiver;
import Beispiel01.Functionalities.Send.Implementation.MessageSenderImplementation;
import Beispiel01.Functionalities.Send.MessageSender;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatServer {

    private void initAndStartServer(int serverPort) {

        try {
            //allows to unlock the socket
            DatagramSocket socket = new DatagramSocket(serverPort);
            MessageBuffer receiveBuffer = new MessageBufferImplementation(100);
            MessageBuffer sendBuffer = new MessageBufferImplementation(100);

            SharedServerState sharedServerState = new SharedServerState(
                    socket, sendBuffer, receiveBuffer
            );

            MessageReceiver receiver = new MessageReceiverImplementation(sharedServerState);

            MessageSender sender = new MessageSenderImplementation(sharedServerState);
            Processor processor = new MessageProcessorImplementation(sharedServerState);

            processor.addHandler(new ConnectMessageHandler(sharedServerState));

            Thread receiverThread = new Thread(receiver);
            Thread senderThread = new Thread(sender);
            Thread processorThread = new Thread(processor);

            senderThread.start();
            processorThread.start();
            receiverThread.start();

            receiverThread.join();


        } catch (InterruptedException e) {

        } catch (SocketException e) {
            System.err.println("Unable to create socket on port: " + serverPort +
                    ", is the port already in use?");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        int port = 9999; //TODO: this should be read from the console
        new ChatServer().initAndStartServer(port);
    }

}
