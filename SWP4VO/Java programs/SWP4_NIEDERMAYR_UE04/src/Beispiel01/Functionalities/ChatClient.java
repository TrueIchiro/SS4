package Beispiel01.Functionalities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChatClient {

    private String chatroom;
    private String nickname;
    private int receivePort;
    private String serverHost;
    private int serverPort;
    private AtomicBoolean running = new AtomicBoolean( true );

    public void startAndInitClient( String[] args )   {
        try {
            chatroom = args[0];
            nickname = args[1];
            receivePort = Integer.valueOf(args[2]);
            serverHost = args[3];
            serverPort = Integer.valueOf(args[4]);

            Thread thread = new Thread(() -> {
                try (DatagramSocket receiver = new DatagramSocket(receivePort)) {
                    while (running.get()) {
                        byte[] buffer = new byte[2048];
                        DatagramPacket pkt = new DatagramPacket(buffer, buffer.length);
                        System.out.print("Client waiting for incoming messages in group: something  - ");
                        receiver.receive(pkt);
                        System.out.println(new String(buffer).trim());
                    }
                } catch (IOException e) {
                    System.err.println( "Failed to receive Paket from server..." );
                }
            });
            thread.start();

            DatagramSocket socket = new DatagramSocket();
            String ConnPacket = "c " + receivePort + " " + chatroom;
            InetAddress addr = InetAddress.getByName(serverHost);
            byte[] ConnMessage = ConnPacket.getBytes();
            DatagramPacket ConnPkt = new DatagramPacket(ConnMessage, ConnMessage.length, addr, serverPort);
            socket.send(ConnPkt);
            Thread.sleep(1000);
            byte[] m = ("n " + nickname + ": It's just a flesh wound.").getBytes();
            DatagramPacket SendPkt = new DatagramPacket(m, m.length, addr, serverPort);
            socket.send(SendPkt);

        } catch( SocketException e ) {
            running.set( false );
            System.err.println( "The client could not be started for server " + serverHost
                    +":"+serverPort + ". Is the server running" );
            e.printStackTrace();

        } catch( UnknownHostException e ) {
            running.set( false );
            System.err.println( "The host " + serverHost + " could not be found!"  );
            e.printStackTrace();
        } catch( InterruptedException | IOException e ) {
            running.set( false );
            e.printStackTrace();
        }
    }

    //reads all the arguments from the console
    private String[] readArgs() throws Exception {
        String[] args = new String[5];

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter the chatroom name.");
        args[0] = reader.readLine();
        System.out.println("Please enter your nickname.");
        args[1] = reader.readLine();
        System.out.println("Please specify the receivePort.");
        args[2] = reader.readLine();
        System.out.println("Please specify the serverHost.");
        args[3] = reader.readLine();
        System.out.println("Please specify the serverPort.");
        args[4] = reader.readLine();

        return args;
    }

    //when you want to read the data from the console
    public void startAndInitClient() throws Exception {
        String[] args = readArgs();
        startAndInitClient(args);
    }

    public static void main(String[] args) throws  Exception {
        //new ChatClient( ).startAndInitClient( args );
        new ChatClient( ).startAndInitClient();
    }

}
