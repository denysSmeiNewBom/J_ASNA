package gui.providers.constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TextPanel {
    public static JPanel getButtonPanel(DefaultTableModel model, JTextField text1,
                                        JTextField text2, JTextField text3) {
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        text1 = new JTextField(14);
        text2 = new JTextField(14);
        text3 = new JTextField(14);
        //Add JTextFields to the panel
        textPanel.add(text1, BorderLayout.NORTH);
        textPanel.add(text2, BorderLayout.CENTER);
        textPanel.add(text3, BorderLayout.SOUTH);
        textPanel.setMinimumSize(new Dimension(40,10));
        textPanel.setMaximumSize(new Dimension(40,30));
        return textPanel;
    }
}
