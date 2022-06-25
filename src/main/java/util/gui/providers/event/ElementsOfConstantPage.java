package util.gui.providers.event;

import util.gui.DTO.TableDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ElementsOfConstantPage {
    public static final String[] columns = {
            "Event", "Condition", "Formula", "Alternative", "Modification", "Info"
    };


    public static JComponent addElementOfConstantPage(DefaultTableModel model, JTextField text1,
                                                      JTextField text2, JTextField text3, JTextField text4,
                                                      JTextField text5, JTextField text6,JTable table,
                                                      TableDTO tableDTO) {
        JComponent jComponent = new JPanel();
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JPanel textPanel = TextPanel.getTextPanel(model, text1, text2, text3, text4, text5, text6);
        //JPanel textPanelForRefuseExp = TextPanel.getTextPanel(model, textForRefuse);

        JPanel buttonPanel = ButtonPanel.getButtonPanel(model, text1, text2, text3, text4, text5, text6, tableDTO);

        //Add panels and table to the main panel
        jComponent.add(textPanel, BorderLayout.NORTH);
        jComponent.add(buttonPanel, BorderLayout.CENTER);
        table.setSize(300, 200);
        jComponent.add(new JScrollPane(table), BorderLayout.CENTER);
        //jComponent.add(textPanelForRefuseExp, BorderLayout.SOUTH);
        return jComponent;
    }
}
