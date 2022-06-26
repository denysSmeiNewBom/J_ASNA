package util.gui.providers.graph;

import util.gui.DTO.TableDTO;
import util.gui.providers.constants.ButtonPanel;
import util.gui.providers.constants.TextPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ElementsOfConstantPage {

    public static JComponent addElementOfConstantPage(DefaultTableModel model, JTable table, TableDTO tableDTO){
        JComponent jComponent = new JPanel();
        /*model = new DefaultTableModel(columns, 0);*/
        table = new JTable(model);
        table.setSize(300,200);
        jComponent.add(new JScrollPane(table), BorderLayout.CENTER);
        return jComponent;
    }
}
