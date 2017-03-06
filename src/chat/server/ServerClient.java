package chat.server;

import java.net.InetAddress;

public class ServerClient {
    public String name;
    public InetAddress address;
    public int port;
    private final int id;
    public int attempt = 0;

    public ServerClient(String name, InetAddress address, int port, int id){
        this.name = name;
        this.address = address;
        this.port = port;
        this.id = id;
    }
}
