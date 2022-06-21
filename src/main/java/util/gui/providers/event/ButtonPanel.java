package util.gui.providers.event;

import util.gui.providers.DTO.EventDTO;
import util.gui.providers.DTO.TableDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel {
    public static JPanel getButtonPanel(DefaultTableModel model, JTextField text1,
                                        JTextField text2, JTextField text3, JTextField text4,
                                        JTextField text5, JTextField text6, TableDTO tableDTO) {
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
                model.addRow(
                        new Object[]{
                                text1.getText(),
                                text2.getText(),
                                text3.getText(),
                                text4.getText(),
                                text5.getText(),
                                text6.getText()
                        }

                );
                tableDTO.getEvents().add(new EventDTO(text1.getText(), text2.getText(), text3.getText(),
                        text4.getText(), text5.getText(), text6.getText()));
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                text6.setText("");
            }
        });

        // This code is called when the Clear button is clicked.
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Clear the form
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                text6.setText("");
            }
        });
        return buttonPanel;
    }
}
