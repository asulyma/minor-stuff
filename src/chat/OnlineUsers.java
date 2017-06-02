package chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OnlineUsers extends JFrame {
    private static final long serialVercionUID = 1L;

    private JPanel contentPane;
    private JList<String> list;

    public OnlineUsers() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(200, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        contentPane.setLayout(gridBagLayout);

        list = new JList<>();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contentPane.add(list, gridBagConstraints);
    }

    public void update(String[] users) {
        list.setListData(users);
    }
}