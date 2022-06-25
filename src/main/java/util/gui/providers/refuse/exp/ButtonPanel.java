package util.gui.providers.refuse.exp;

import util.gui.DTO.EventDTO;
import util.gui.DTO.TableDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel {
    public static JPanel getButtonPanel(DefaultTableModel model, JTextField refuseText, TableDTO tableDTO) {
        JButton addButton = new JButton("+ Add");
        //Clear button
        JButton clearButton = new JButton("Clear");
        //Button panel
        JPanel buttonPanel = new JPanel();
        //Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        // This code is called when the Add button is clicked.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add form data
                tableDTO.setRefuseExpression(refuseText.getText());
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Clear the form
                refuseText.setText("");
            }
        });
        return buttonPanel;
    }
}
