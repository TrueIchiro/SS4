package Beispiel01.Functionalities;

import Beispiel01.Functionalities.Buffer.MessageBuffer;
import Beispiel01.Functionalities.Message.Message;

import java.net.DatagramSocket;
import java.util.*;

public class SharedServerState {

    private Map<String, List<Client>> clientGroups = Collections.synchronizedMap(new HashMap<>());
    private List<Client> clients = Collections.synchronizedList(new ArrayList<>());
    private DatagramSocket serverSocket;
    private MessageBuffer sendMessageBuffer;
    private MessageBuffer receiveMessageBuffer;

    public SharedServerState( DatagramSocket serverSocket, MessageBuffer sendMessageBuffer, MessageBuffer receiveMessageBuffer ) {
        this.serverSocket = serverSocket;
        this.sendMessageBuffer = sendMessageBuffer;
        this.receiveMessageBuffer = receiveMessageBuffer;
    }

    public synchronized void addClient( Client client ) {
        this.clients.add( client );
        this.clientGroups.computeIfAbsent( client.getGroup(), tmp -> new ArrayList<>() );
        this.clientGroups.get( client.getGroup() ).add( client );
    }

    public synchronized  List<Client> getClientsInGroup(String group ) {
        return this.clientGroups.get( group );
    }

    public synchronized  Client getClientForMessage(Message message) {
        for( Client client : clients ) {
            if( client.getPort() == message.getPort() && message.getHost().equals( client.getHost() ) )
                return client;
        }
        return null;
    }

    public synchronized DatagramSocket getServerSocket() {
        return serverSocket;
    }

    public synchronized  MessageBuffer getSendMessageBuffer() {
        return sendMessageBuffer;
    }

    public synchronized  MessageBuffer getReceiveMessageBuffer() {
        return receiveMessageBuffer;
    }

}
