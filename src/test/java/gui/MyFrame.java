package gui;

import gui.providers.MenuBar;
import gui.providers.constants.ButtonPanel;
import gui.providers.constants.ElementsOfConstantPage;
import gui.providers.constants.TextPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    private JTextField text1, text2, text3/*, text4*/;
    private DefaultTableModel model;
    private JTable table;
    private JTabbedPane tablePanel;

    public MyFrame() throws HeadlessException {
        ElementsOfConstantPage.addElementOfConstantPage(this,model,text1,text2,text3,table);







        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        menuBar = MenuBar.getMenuBar();
        this.setJMenuBar(menuBar);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Salut!");
    }
}
