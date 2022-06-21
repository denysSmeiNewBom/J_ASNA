package util.gui.providers.vector;

import util.gui.providers.DTO.TableDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ElementsOfConstantPage {
    public static final String[] columns = {
            "Name","Value","Info"
    };


    public static JComponent addElementOfConstantPage(DefaultTableModel model, JTextField text1,
                                                      JTextField text2, JTextField text3, JTable table, TableDTO tableDTO){
        JComponent jComponent = new JPanel();
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JPanel textPanel = TextPanel.getTextPanel(model,text1,text2,text3);

        JPanel buttonPanel = ButtonPanel.getButtonPanel(model,text1,text2,text3, tableDTO);

        //Add panels and table to the main panel
        jComponent.add(textPanel, BorderLayout.NORTH);
        jComponent.add(buttonPanel, BorderLayout.SOUTH);
        table.setSize(300,200);
        jComponent.add(new JScrollPane(table), BorderLayout.CENTER);
        return jComponent;
    }
}
