package util.gui.providers.constants;

import util.gui.DTO.ConstantDTO;
import util.gui.DTO.TableDTO;
import util.gui.validator.in.table.ITableValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel {
    static ITableValidator tableValidator;

    public static ITableValidator getTableValidator() {
        return tableValidator;
    }

    public static void setTableValidator(ITableValidator tableValidator) {
        ButtonPanel.tableValidator = tableValidator;
    }

    public static JPanel getButtonPanel(DefaultTableModel model, JTextField text1,
                                        JTextField text2, JTextField text3, TableDTO tableDTO) {
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
                /*if (tableValidator.validate()) {*/
                String t1 = text1.getText();
                String t2 = text2.getText();
                String t3 = text3.getText();
                model.addRow(
                        new Object[]{
                                t1, t2, t3
                        }

                );
                tableDTO.getConstants().add(new ConstantDTO(t1, t2, t3));
                text1.setText("");
                text2.setText("");
                text3.setText("");
                /*} else {
                    System.out.println("WrongInput!");
                }*/
            }
        });

        // This code is called when the Clear button is clicked.
        clearButton.addActionListener(new

                                              ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      //Clear the form
                                                      text1.setText("");
                                                      text2.setText("");
                                                      text3.setText("");
                                                  }
                                              });
        return buttonPanel;
    }
}
