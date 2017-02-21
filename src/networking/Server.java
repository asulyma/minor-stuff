package networking;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by local on 21-Feb-17.
 */
public class Server implements Runnable {
    private static Socket connectiom;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static ServerSocket server;

    @Override
    public void run() {
        try {
            server = new ServerSocket(5678, 10);
            while (true) {
                connectiom = server.accept();
                output = new ObjectOutputStream(connectiom.getOutputStream());
                input = new ObjectInputStream(connectiom.getInputStream());
                output.writeObject("Message: "+ input.readObject());
            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (HeadlessException e) {
        } catch (ClassNotFoundException e){}
    }
}