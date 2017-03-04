package chat.server;

/**
 * This class is responsible for creating and server logic.
 */

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    private int port;
    private Thread serverRun, manage, receive;
    private boolean running = false;
    private DatagramSocket socket;

    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        serverRun = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                manage();
                receive();
            }
        }, "serverRun");
    }

    private void manage() {
        manage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    //TODO: manage the clients
                }
            }
        }, "manage");
        manage.start();
    }

    private void receive() {
        receive = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    //TODO: receive
                }
            }
        }, "receive");
        receive.start();
    }

}
