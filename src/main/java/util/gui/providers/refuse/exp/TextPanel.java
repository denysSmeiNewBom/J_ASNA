package util.gui.providers.refuse.exp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TextPanel {
    public static JPanel getTextPanel(DefaultTableModel model,JTextField textForRefuse){
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Add JTextFields to the panel
        textPanel.add(textForRefuse, BorderLayout.CENTER);
        textPanel.setMinimumSize(new Dimension(40,10));
        textPanel.setMaximumSize(new Dimension(40,30));
        return textPanel;
    }
}
