package chat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Networking {

    private DatagramSocket socket = null;
    private InetAddress ip = null;
    private Thread send;
    private int port;
    private int ID = -1;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Networking(int port) {
        this.port = port;
    }

    public boolean openConnection(String address) {
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void close() {
        new Thread() {
            public void run() {
                synchronized (socket) {
                    socket.close();
                }
            }
        }.start();
    }

    public String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //byte[] tmp = packet.getData();
        //String tmp2 = new String(packet.getData());
        //TODO BUG THIS
        return new String(packet.getData());
    }

    public void send(final byte[] data) {
        send = new Thread("Send") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        send.start();
    }

}