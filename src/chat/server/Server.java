package chat.server;

/**
 * This class is responsible for creating and server logic.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ServerClient> clients = new ArrayList<ServerClient>();
    private int port;
    private DatagramSocket socket;
    private Thread serverRun, manage, receive, send;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        serverRun = new Thread(() -> {
            running = true;
            System.out.println("Server started on port " + port);
            manage();
            receive();
        }, "serverRun");

        serverRun.start();
    }

    private void manage() {
        manage = new Thread(() -> {
            while (running) {
                //System.out.println(clients.size());
            }
        }, "manage");
        manage.start();
    }

    private void receive() {
        receive = new Thread(() -> {
            while (running) {
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                process(packet);
            }
        }, "receive");
        receive.start();
    }

    private void send(String message, InetAddress address, int port) {
        message += "/e/";
        send(message.getBytes(), address, port);
    }

    private void process(DatagramPacket packet) {
        String str = new String(packet.getData());
        if (str.startsWith("/c/")) {
            int id = UniqueID.getID();
            System.out.println("ID:" + id);
            clients.add(new ServerClient(str.substring(3, str.length()), packet.getAddress(), packet.getPort(), id));
            System.out.println(str.substring(3, str.length()));
            String ID = "/c/" + id;
            send(ID, packet.getAddress(), packet.getPort());
        } else if (str.startsWith("/m/")) {
            sendToAll(str);
        } else if (str.startsWith("/d/")) {
            String id = str.split("/d/|/e/")[1];
            disconnect(Integer.parseInt(id), true);
        } else {
            System.out.println(str);
        }
    }

    private void disconnect(int id, boolean status) {
        ServerClient s = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getID() == id) {
                s = clients.get(i);
                clients.remove(i);
                break;
            }
        }
        String mess = "";
        if (status) {
            mess = "Client " + s.name + " (" + s.getID() + ") @ " + s.address.toString() + ":" + s.port + " disconnected.";
        } else
            mess = "Client " + s.name + " (" + s.getID() + ") @ " + s.address.toString() + ":" + s.port + " time out.";
        System.out.println(mess);
    }

    private void sendToAll(String message) {
        for (int i = 0; i < clients.size(); i++) {
            ServerClient serverClient = clients.get(i);
            send(message.getBytes(), serverClient.address, serverClient.port);
        }
    }

    private void send(final byte[] data, final InetAddress addr, final int port) {
        send = new Thread("Send") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                }
            }
        };
        send.start();
    }
}