package networking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A simple example of interaction with the server. The request is sent to the server, then returns.
 */
public class Networking extends JFrame implements Runnable {
    private static Socket connectiom;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public static void main(String[] args) {
        new Thread(new Networking("Send message")).start();
        new Thread(new Server()).start();

    }

    Networking(String name) {
        super(name);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        final JTextField jTextField = new JTextField(10);
        final JButton jButton = new JButton("Send");
        jButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == jButton) {
                sendData(jTextField.getText());
            }
        });
        add(jTextField);
        add(jButton);
        jTextField.updateUI();
        jButton.updateUI();
    }

    @Override
    public void run() {
        try {
            while (true) {
                connectiom = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                output = new ObjectOutputStream(connectiom.getOutputStream());
                input = new ObjectInputStream(connectiom.getInputStream());
                JOptionPane.showMessageDialog(null, input.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void sendData(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}