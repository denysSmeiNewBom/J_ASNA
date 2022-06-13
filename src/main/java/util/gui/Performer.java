package util.gui;

import util.chart.ChartBuilder;
import util.compile.Compiler;
import util.compile.impl.CompilerImplV1;
import util.graph.State;
import util.graph.builder.GraphBuilder;
import util.io.ConfigReader;
import util.parser.impl.GraphBuilderParserImplV1;
import util.rkm.Pdto;
import util.rkm.RKM;

import java.util.List;


public class Performer {
    private static final String CONFIGURATION_PATH = "src/main/resources/config.txt";
    private static final String CLASS_NAME = "GraphTestDebug";

    public static void execute(){
        String conf = ConfigReader.readFile(CONFIGURATION_PATH);
        String classDeclare = new GraphBuilderParserImplV1().getExtendedClass(CLASS_NAME, conf);
        System.out.println(classDeclare);
        Compiler compiler = new CompilerImplV1(new GraphBuilderParserImplV1());
        GraphBuilder graphBuilder = compiler.getBuilder(CLASS_NAME,conf);
        List<State> graph = graphBuilder.getWholeGraph();
        System.out.println("Size of graph: " + graph.size());
        System.out.println(graph);
        RKM rkm = new RKM(graph);
        Pdto pdto = rkm.calculateRCM();
        double[] yi = pdto.getYi();
        double sumGood = 0;
        double sumBad = 0;
        int iterGood = 0;
        int iterBad = 0;
        for (int i = 0; i < yi.length; i++) {
            if (graph.get(i).getVector()[0] >= RKM.WORKING_STATES){
                sumGood += yi[i];
                iterGood++;
            }else {
                sumBad += yi[i];
                iterBad++;
            }
        }
        System.out.println("Значення функції готовності: " + Math.abs(sumGood));
        System.out.println("Кількість працездатних станів: " + iterGood);
        System.out.println("Ймовірність виходу з ладу системи:" + sumBad);
        System.out.println("Кількість непрацездатних станів: " + iterBad);
        System.out.println("Закальна кількість станів: " + graph.size());
        System.out.print("x = [" + pdto.getT().get(0));
        for (int i = 1; i < pdto.getT().size(); i++) {
            System.out.print("," + pdto.getT().get(i));
        }
        System.out.println("]");

        System.out.print("y = [" + pdto.getY().get(0));
        for (int i = 1; i < pdto.getY().size(); i++) {
            System.out.print("," + pdto.getY().get(i));
        }
        System.out.println("]");
        ChartBuilder.buildChart(pdto.getT(),pdto.getY());
    }
}
