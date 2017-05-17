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


    public Client(String name, String addr, int port) {
        setTitle("Messenger Client");
        this.name = name;
        this.address = addr;
        this.port = port;

        networking = new Networking(port);
        connected = networking.openConnection(address);

        createWindow();
        String connectionPacket = "/c/" + name;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setResizable(false);
        setLocationRelativeTo(null);
        log.info("Create window...");

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{16, 857, 7};
        gbl_contentPane.columnWidths = new int[]{16, 827, 30, 7};
        gbl_contentPane.rowHeights = new int[]{35, 475, 40};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
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
        scrollConstraints.insets = new Insets(0, 7, 0, 0);
        contentPane.add(scrollPane, scrollConstraints);

        //When you press "Enter"
        txtMessage = new JTextField();
        txtMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send(txtMessage.getText());
                }
            }
        });

        GridBagConstraints gbc_txtMessage = new GridBagConstraints();
        gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
        gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMessage.gridx = 0;
        gbc_txtMessage.gridy = 2;
        gbc_txtMessage.gridwidth = 2;
        contentPane.add(txtMessage, gbc_txtMessage);
        txtMessage.setColumns(10);

        //Clicking button "Send"
        btnSend = new JButton("Send");
        btnSend.addActionListener((e) -> send(txtMessage.getText()));

        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 0, 5);
        gbc_btnSend.gridx = 2;
        gbc_btnSend.gridy = 2;
        contentPane.add(btnSend, gbc_btnSend);
        setVisible(true);

        txtMessage.requestFocusInWindow();
        log.info("Window is created.");
    }

    //Formation of the message.
    public void send(String message) {
        if (message.equals("")) return;
        message = name + ": " + message;
        //console(message);
        message = "/m/" + message;
        networking.send(message.getBytes());
        txtMessage.setText("");
        log.info("Send message.");
    }

    //Transferring the connection to the server.
    public void listen() {
        listen = new Thread("Listen") {
            public void run() {
                while (running) {
                    String message = networking.receive();
                    if (message.startsWith("/c/")) {
                        networking.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
                        console("Successfully connected to server! ID: " + networking.getID());
                        log.info("Successfully connected to server! ID: " + networking.getID());
                    } else if (message.startsWith("/m/")) {
                        String text = message.split("/m/|/e/")[1];
                        console(text);
                    }
                }
            }
        };
        listen.start();
    }

    //Outputting messages.

    public void console(String message) {
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
