package util.gui.providers;


import util.gui.Performer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {
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

    public static JMenuBar getMenuBar(){
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
                Performer.execute();
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
}
