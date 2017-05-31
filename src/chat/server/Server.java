package chat.server;

/**
 * This class is responsible for creating and server logic.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private List<ServerClient> clients = new ArrayList<>();
    private List<Integer> clientResponce = new ArrayList<>();
    private int port;
    private DatagramSocket socket;
    private Thread serverRun, manage, receive, send;
    private boolean running = false, row = false;

    private final int MAX_ATTEMPTS = 5;

    Server(int port) {
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
            Scanner scanner = new Scanner(System.in);
            while (running) {
                String text = scanner.nextLine();
                if (!text.startsWith("/")) {
                    sendToAll("/m/Server: " + text + "/e/");
                    continue;
                }
                text = text.substring(1);
                if (text.equals("row")) {                                   //server commands
                    row = !row;
                } else if (text.equals("clients")) {                        //server commands
                    System.out.println("Clients:");
                    System.out.println("========");
                    for (ServerClient c : clients) {
                        System.out.println(c.name + "( " + c.getID() + " ): " + c.address.toString() + ":" + c.port);
                    }
                    System.out.println("========");
                } else if (text.startsWith("kick")) {
                    String name = text.split(" ")[1];
                    int id = -1;
                    boolean num = true;
                    try {
                        id = Integer.parseInt(name);
                    } catch (NumberFormatException e) {
                        num = false;
                    }
                    if (num) {
                        boolean exists = false;
                        for (ServerClient client : clients) {
                            if (client.getID() == id) {
                                exists = true;
                                break;
                            }
                        }
                        if (exists)
                            disconnect(id, true);
                        else
                            System.out.println("Client " + id + " doesnt exist! Check ID.");
                    } else {
                        for (ServerClient c : clients) {
                            if (name.equals(c.name)) {
                                disconnect(c.getID(), true);
                                break;
                            }
                        }
                    }
                }
            }
        }, "serverRun");

        serverRun.start();
    }

    private void manage() {
        manage = new Thread(() -> {
            while (running) {
                sendToAll("/i/server");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (ServerClient c : clients) {
                    if (!clientResponce.contains(c.getID())) {
                        if (c.attempt >= MAX_ATTEMPTS) {
                            disconnect(c.getID(), false);
                        } else {
                            c.attempt++;
                        }
                    } else {
                        clientResponce.remove(new Integer(c.getID()));
                        c.attempt = 0;
                    }
                }
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
        if (row) System.out.println(str);
        if (str.startsWith("/c/")) {
            int id = UniqueID.getID();
            String name = str.split("/c/|/e/")[1];

            System.out.println(name + " (" + id + ") connected.");
            clients.add(new ServerClient(name, packet.getAddress(), packet.getPort(), id));
            String ID = "/c/" + id;
            send(ID, packet.getAddress(), packet.getPort());
        } else if (str.startsWith("/m/")) {
            sendToAll(str);
        } else if (str.startsWith("/d/")) {
            String id = str.split("/d/|/e/")[1];
            disconnect(Integer.parseInt(id), true);
        } else if (str.startsWith("/i/")) {
            clientResponce.add(Integer.parseInt(str.split("/i/|/e/")[1]));
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
        String mess;
        if (status) {
            mess = "Client " + s.name + " (" + s.getID() + ") @ " + s.address.toString() + ":" + s.port + " disconnected.";
        } else
            mess = "Client " + s.name + " (" + s.getID() + ") @ " + s.address.toString() + ":" + s.port + " time out.";
        System.out.println(mess);
    }

    private void sendToAll(String message) {
        if (message.startsWith("/m/")) {
            String text = message.substring(3);
            text = text.split("/e/")[0];
            System.out.println(message);
        }

        for (ServerClient serverClient : clients) {
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
                    System.out.println("Sending error!");
                }
            }
        };
        send.start();
    }
}