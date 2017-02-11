/*
 * Created by JFormDesigner on Thu Feb 09 21:43:04 EET 2017
 */

package swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author unknown
 */
public class Builder extends JFrame {
    public Builder() {
        initComponents();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void getImageActionPerformed(ActionEvent e) {
        System.out.println("Ok/GetImage");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Aleksandr Sulyma
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        panel2 = new JPanel();
        button3 = new JButton();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "10dlu, $lcgap, 100dlu, $lcgap, 158dlu, $lcgap, 11dlu",
            "18dlu, $lgap, 30dlu, $lgap, 108dlu, $lgap, 9dlu"));

        //======== tabbedPane1 ========
        {

            //======== panel1 ========
            {

                // JFormDesigner evaluation mark
                panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                panel1.setLayout(new FormLayout(
                    "82dlu, $lcgap, 95dlu, $lcgap, 77dlu",
                    "18dlu, $lgap, 22dlu, $lgap, 24dlu, $lgap, 52dlu"));

                //---- button1 ----
                button1.setText("Get Image");
                button1.addActionListener(e -> getImageActionPerformed(e));
                panel1.add(button1, CC.xy(1, 3));

                //---- button2 ----
                button2.setText("Get File");
                panel1.add(button2, CC.xy(5, 3));

                //======== scrollPane1 ========
                {

                    //---- textArea1 ----
                    textArea1.setText("Enter URL: ");
                    scrollPane1.setViewportView(textArea1);
                }
                panel1.add(scrollPane1, CC.xywh(1, 6, 5, 2));
            }
            tabbedPane1.addTab("Tab 1", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                    "44dlu, $lcgap, 38dlu, $lcgap, 62dlu, $lcgap, 29dlu, $lcgap, 73dlu",
                    "32dlu, $lgap, 40dlu, $lgap, 38dlu"));

                //---- button3 ----
                button3.setText("View");
                panel2.add(button3, CC.xy(1, 1));

                //---- label1 ----
                label1.setText("Format: ");
                panel2.add(label1, CC.xy(7, 1));

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\"png\"",
                    "\"jpg\""
                }));
                panel2.add(comboBox1, CC.xy(9, 1));
            }
            tabbedPane1.addTab("Tab 2", panel2);
        }
        contentPane.add(tabbedPane1, CC.xywh(3, 3, 3, 3));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Aleksandr Sulyma
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel panel2;
    private JButton button3;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
