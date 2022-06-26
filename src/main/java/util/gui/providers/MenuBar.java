package util.gui.providers;


import util.graph.State;
import util.gui.Performer;
import util.gui.DTO.TableDTO;
import util.io.ConfigReader;
import util.parser.IParseUiDtoToConfig;
import util.parser.impl.ParserUiDtoToConfigJavaStyle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuBar {
    private static final String CONFIGURATION_PATH = "src/main/resources/config.txt";
    static IParseUiDtoToConfig toConfig;
    static JMenuBar menuBar;
    static JMenu project;
    static JMenu output;
    static JMenu help;
    static JMenuItem neW;
    static JMenuItem open;
    static JMenuItem save;
    static JMenuItem exit;
    static JMenuItem calculate;
    static JMenuItem buildGraph;
    static JMenuItem export;
    static JMenuItem manual;
    static TableDTO tableDTO;/*
    static DefaultTableModel outPutTableModel;*/
    static List<State> states;

    private MenuBar() {
    }

    public static JMenuBar getMenuBar(TableDTO tableDTO, DefaultTableModel outPutTableModel) {
        MenuBar.tableDTO = tableDTO;
        /*MenuBar.outPutTableModel = outPutTableModel;*/
        toConfig = new ParserUiDtoToConfigJavaStyle();
        menuBar = new JMenuBar();

        project = new JMenu("Project");
        output = new JMenu("Output");
        help = new JMenu("Help");

        neW = new JMenuItem("New");
        open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening Of File");
            }
        });
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        project.add(neW);
        project.add(open);
        project.add(save);
        project.add(exit);

        calculate = new JMenuItem("Calculate");
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Executing started");
                if (!tableDTO.isEmpty()) {
                    states = Performer.execute(toConfig.parseDtoToConfig(tableDTO));
                } else {
                    states = Performer.execute(ConfigReader.readFile(CONFIGURATION_PATH));
                }
                fillOutTable(outPutTableModel);
            }
        });
        buildGraph = new JMenuItem("Build Graph");
        export = new JMenuItem("Export...");

        output.add(calculate);
        output.add(buildGraph);
        output.add(export);

        manual = new JMenuItem("Manual");

        help.add(manual);

        menuBar.add(project);
        menuBar.add(output);
        menuBar.add(help);

        return menuBar;
    }

    private static void fillOutTable(DefaultTableModel outPutTableModel) {
        int index = 0;
        for (State state : states) {
            String vector = "[";
            for (int v : state.getVector()) {
                vector = vector + v + ",";
            }
            vector = vector.substring(0, vector.length() - 1);
            vector = vector + "]";
            Object[] o = new Object[3];
            o[0] = String.valueOf(index++);
            o[1] = vector;
            o[2] = String.valueOf(state.getIntensityInValue());
            outPutTableModel.addRow(o);
        }
    }
}
