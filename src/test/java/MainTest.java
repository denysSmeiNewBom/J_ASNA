import util.compile.Compiler;
import util.graph.State;
import util.compile.impl.CompilerImplV1;
import util.graph.builder.GraphBuilder;
import util.graph.builder.test.GraphTest;
import util.io.ConfigReader;
import util.parser.impl.GraphBuilderParserImplV1;
import util.rkm.RKM;

import java.util.List;


public class MainTest {
    private static final String CONFIGURATION_PATH = "src/main/resources/config.txt";
    private static final String CLASS_NAME = "GraphTestDebug";

    public static void main(String[] args) {
        String conf = ConfigReader.readFile(CONFIGURATION_PATH);
        String classDeclare = new GraphBuilderParserImplV1().getExtendedClass(CLASS_NAME, conf);
        System.out.println(classDeclare);
        Compiler compiler = new CompilerImplV1(new GraphBuilderParserImplV1());
        GraphBuilder graphBuilder = compiler.getBuilder(CLASS_NAME,conf);
        //GraphTest graphTest = new GraphTest();
        List<State> graph = graphBuilder.getWholeGraph();


        System.out.println(graph);
        RKM rkm = new RKM(graph);
        rkm.calculateRCM();


        //System.out.println(graphBuilder.getClass());
    }
    //TODO  R-K-method  Рунге-Кутти-Мерсона (Адаптивним кроком) (RKM in Assembler ASNA)
    //TODO  ГРАФІК!!
    //TODO* Побудова графу з матриці інтенсивності
}
