package util.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu project;
    JMenu output;
    JMenu help;
    JMenuItem neW;
    JMenuItem open;
    JMenuItem save;
    JMenuItem exit;
    JMenuItem calculate;
    JMenuItem buildGraph;
    JMenuItem export;
    JMenuItem manual;

    public MyFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();

        project = new JMenu("Project");
        output = new JMenu("Output");
        help = new JMenu("Help");

        neW = new JMenuItem("New");
        open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nothing");
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

        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
