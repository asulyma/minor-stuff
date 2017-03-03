package chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtAddress;
    private JTextField txtPort;
    private JTextField txtName;
    private JLabel lblName;
    private JLabel lblIpAddress;
    private JLabel lblPort;
    private JLabel lblAddressDesc;
    private JLabel lblPortDesc;
    private JButton btnLogin;

    public Login() {
        // Responsible for how the window will look like.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Add items to window.
        txtName = new JTextField();
        txtName.setBounds(79, 60, 142, 26);
        contentPane.add(txtName);
        txtName.setColumns(10);

        lblName = new JLabel("Name: ");
        lblName.setBounds(129, 45, 61, 16);
        contentPane.add(lblName);

        lblIpAddress = new JLabel("IP Address: ");
        lblIpAddress.setBounds(121, 98, 78, 16);
        contentPane.add(lblIpAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(79, 114, 142, 26);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        txtPort = new JTextField();
        txtPort.setBounds(79, 184, 142, 26);
        contentPane.add(txtPort);
        txtPort.setColumns(10);

        lblPort = new JLabel("Port: ");
        lblPort.setBounds(135, 168, 40, 16);
        contentPane.add(lblPort);

        lblAddressDesc = new JLabel("ex: 192.168.0.2");
        lblAddressDesc.setBounds(105, 140, 110, 16);
        contentPane.add(lblAddressDesc);

        lblPortDesc = new JLabel("ex: 7787");
        lblPortDesc.setBounds(123, 209, 70, 16);
        contentPane.add(lblPortDesc);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(88, 250, 117, 29);
        contentPane.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String address = txtAddress.getText();
                int port;
                try {
                    port = Integer.parseInt(txtPort.getText());
                    login(name, address, port);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Enter correct data!");
                }
            }
        });

    }

    // Closing the current window and the creation of the client window.
    private void login(String name, String address, int port) {
        dispose();
        new Client(name, address, port);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
