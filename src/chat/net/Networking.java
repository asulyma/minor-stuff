package chat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Networking {
    private DatagramSocket socket = null;
    private InetAddress ip = null;
    private Thread send;
    private int port;

    public Networking(int port){
        this.port = port;
    }

    public boolean openConnection(String address){
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void send(final byte[] data){
        send = new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        send.start();
    }

    public String receive(){
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }
}
