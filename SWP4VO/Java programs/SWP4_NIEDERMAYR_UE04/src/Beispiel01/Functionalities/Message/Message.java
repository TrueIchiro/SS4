package Beispiel01.Functionalities.Message;

public class Message {

    private String host;
    private int port;
    private char command;
    private String payload;

    public Message(String host, int port, char command, String payload) {
        this.host = host;
        this.port = port;
        this.command = command;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", command=" + command +
                ", payload='" + payload + '\'' +
                '}';
    }

    //Getter
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public char getCommand() {
        return command;
    }

    public String getPayload() {
        return payload;
    }

    //Setter
    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setCommand(char command) {
        this.command = command;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
