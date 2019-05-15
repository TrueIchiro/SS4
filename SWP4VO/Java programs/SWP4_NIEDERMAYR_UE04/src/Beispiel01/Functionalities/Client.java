package Beispiel01.Functionalities;

public class Client {

    private String host;
    private int port;
    private int receivingPort;
    private String group;

    public Client(String host, int port, int receivingPort, String group) {
        this.host = host;
        this.port = port;
        this.receivingPort = receivingPort;
        this.group = group;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getReceivingPort() {
        return receivingPort;
    }

    public void setReceivingPort(int receivingPort) {
        this.receivingPort = receivingPort;
    }

}
