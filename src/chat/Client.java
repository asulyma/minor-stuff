package chat;

/**
 * The first setting. Create UI.
 */

import chat.net.Networking;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(Client.class.getName());

    private JPanel contentPane;
    private String name;
    private String address;
    private int port;
    private JTextField txtMessage;
    private JTextArea history;
    private JButton btnSend;

    private Networking networking;
    private boolean connected = false;

    private Thread listen, run;
    private boolean running = false;


    Client(String name, String addr, int port) {
        setTitle("Messenger Client");
        this.name = name;
        this.address = addr;
        this.port = port;

        networking = new Networking(port);
        connected = networking.openConnection(address);

        createWindow();
        String connectionPacket = "/c/" + name + "/e/";
        networking.send(connectionPacket.getBytes());
        console("Connecting to: " + addr + "\tPort: " + port + "\tUser: " + name);
        if (!connected) console("Connection failed...");

        running = true;
        run = new Thread(this, "Running");
        run.start();
    }

    private void createWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(880, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        //setResizable(false);
        setLocationRelativeTo(null);
        log.info("Create window...");

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{16, 827, 30, 7};
        gbl_contentPane.rowHeights = new int[]{35, 475, 40};
        contentPane.setLayout(gbl_contentPane);

        history = new JTextArea();
        history.setEditable(false);
        history.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(history);
        GridBagConstraints scrollConstraints = new GridBagConstraints();
        scrollConstraints.insets = new Insets(0, 0, 5, 5);
        scrollConstraints.fill = GridBagConstraints.BOTH;
        scrollConstraints.gridx = 0;
        scrollConstraints.gridx = 0;
        scrollConstraints.gridwidth = 3;
        scrollConstraints.gridheight = 2;
        scrollConstraints.weightx = 1;
        scrollConstraints.weighty = 1;
        scrollConstraints.insets = new Insets(0, 7, 0, 0);
        contentPane.add(scrollPane, scrollConstraints);

        //When you press "Enter"
        txtMessage = new JTextField();
        txtMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send(txtMessage.getText(), true);
                }
            }
        });

        GridBagConstraints gbc_txtMessage = new GridBagConstraints();
        gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
        gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMessage.gridx = 0;
        gbc_txtMessage.gridy = 2;
        gbc_txtMessage.gridwidth = 2;
        gbc_txtMessage.weightx = 1;
        gbc_txtMessage.weighty = 0;
        contentPane.add(txtMessage, gbc_txtMessage);
        txtMessage.setColumns(10);

        //Clicking button "Send"
        btnSend = new JButton("Send");
        btnSend.addActionListener((e) -> send(txtMessage.getText(), true));

        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 0, 5);
        gbc_btnSend.gridx = 2;
        gbc_btnSend.gridy = 2;
        gbc_txtMessage.weightx = 0;
        gbc_txtMessage.weighty = 0;
        contentPane.add(btnSend, gbc_btnSend);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String disconnect = "/d/" + networking.getID() + "/e/";
                send(disconnect, false);
                networking.close();
                running = false;
                log.info("Closed!");
            }
        });

        setVisible(true);

        txtMessage.requestFocusInWindow();
        log.info("Window is created.");
    }

    //Formation of the message.
    private void send(String message, boolean text) {
        if (message.equals("")) return;

        if (text) {
            message = name + ": " + message;
            message = "/m/" + message;
            txtMessage.setText("");
            log.info("Send message.");
        }
        networking.send(message.getBytes());
    }

    //Transferring the connection to the server.
    private void listen() {
        listen = new Thread("Listen") {
            public void run() {
                while (running) {
                    String message = networking.receive();
                    if (message.startsWith("/c/")) {
                        networking.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
                        console("Successfully connected to server! ID: " + networking.getID());
                        log.info("Successfully connected to server! ID: " + networking.getID());
                    } else if (message.startsWith("/m/")) {
                        String text = message.substring(3);
                        text = text.split("/e/")[0];
                        console(text);
                    } else if (message.startsWith("/i/")) {
                        String text = "/i/" + networking.getID() + "/e/";
                        send(text, false);
                    }
                }
            }
        };
        listen.start();
    }

    //Outputting messages.

    private void console(String message) {
        //Correct scrolling
        history.setCaretPosition(history.getDocument().getLength());
        //Append message
        history.append(message + "\n\r");
    }

    @Override
    public void run() {
        listen();
    }
}
