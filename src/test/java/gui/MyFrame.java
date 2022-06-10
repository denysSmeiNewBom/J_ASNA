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
    private JTextField constantText1 = new JTextField(), constantText2 = new JTextField(), constantText3 = new JTextField();
    private JTextField vectorText1, vectorText2, vectorText3;
    private JTextField eventText1, eventText2, eventText3, eventText4, eventText5, eventText6;
    private DefaultTableModel constantModel = new DefaultTableModel();
    private DefaultTableModel vectorModel;
    private DefaultTableModel eventModel;
    private JTable constantTable = new JTable();
    private JTable vectorTable;
    private JTable eventTable;
    private JTabbedPane tablePanel;

    public MyFrame() throws HeadlessException {
        //ElementsOfConstantPage.addElementOfConstantPage(this,model,text1,text2,text3,table);


        tablePanel = new JTabbedPane();
        JComponent constantComp = ElementsOfConstantPage
                .addElementOfConstantPage(constantModel, constantText1, constantText2, constantText3, constantTable);
        tablePanel.addTab("Constants and value", null, constantComp, "More text1");
        JComponent vectorComponent = gui.providers.vector.ElementsOfConstantPage
                .addElementOfConstantPage(vectorModel, vectorText1, vectorText2, vectorText3, vectorTable);
        tablePanel.addTab("Vectors and refuse expressions", null, vectorComponent, "More text2");
        JComponent eventComponent = gui.providers.event.ElementsOfConstantPage
                .addElementOfConstantPage(eventModel, eventText1, eventText2, eventText3,
                        eventText4, eventText5, eventText6, eventTable);
        tablePanel.addTab("Event tree", null, eventComponent, "More text3");
        //tabbedPane.setLayout();
        getContentPane().add(tablePanel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        menuBar = MenuBar.getMenuBar();
        this.setJMenuBar(menuBar);
        //this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Salut!");
    }
}
