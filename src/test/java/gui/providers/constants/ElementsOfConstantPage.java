package gui.providers.constants;

import gui.MyFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ElementsOfConstantPage {
    public static final String[] columns = {
            "Name","Value","Info"
    };


    public static void addElementOfConstantPage(MyFrame frame, DefaultTableModel model, JTextField text1,
                                                JTextField text2, JTextField text3, JTable table){
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JPanel buttonPanel = ButtonPanel.getButtonPanel(model,text1,text2,text3);

        JPanel textPanel = TextPanel.getButtonPanel(model,text1,text2,text3);

        //Add panels and table to the main panel
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        table.setSize(300,200);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
