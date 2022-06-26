package table.fill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyF extends JFrame {


    public MyF() throws HeadlessException {
        JPanel jPanel = new JPanel();
        DefaultTableModel defaultTableModel = new DefaultTableModel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
    }
}
