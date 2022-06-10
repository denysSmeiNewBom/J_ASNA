package gui.providers.event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TextPanel {
    public static JPanel getTextPanel(DefaultTableModel model, JTextField text1,
                                      JTextField text2, JTextField text3, JTextField text4,
                                      JTextField text5, JTextField text6) {
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Add JTextFields to the panel
        textPanel.add(text1, BorderLayout.CENTER);
        textPanel.add(text2, BorderLayout.CENTER);
        textPanel.add(text3, BorderLayout.CENTER);
        textPanel.add(text4, BorderLayout.CENTER);
        textPanel.add(text5, BorderLayout.CENTER);
        textPanel.add(text6, BorderLayout.CENTER);
        textPanel.setMinimumSize(new Dimension(40,10));
        textPanel.setMaximumSize(new Dimension(40,30));
        return textPanel;
    }
}
