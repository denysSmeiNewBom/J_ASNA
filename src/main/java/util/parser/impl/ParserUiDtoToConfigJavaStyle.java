package util.parser.impl;

import util.gui.DTO.ConstantDTO;
import util.gui.DTO.EventDTO;
import util.gui.DTO.TableDTO;
import util.gui.DTO.VectorDTO;
import util.parser.IParseUiDtoToConfig;

import java.util.List;

public class ParserUiDtoToConfigJavaStyle implements IParseUiDtoToConfig<TableDTO> {
    private static String CONSTANT = "_Const_";
    private static String VECTOR = "_Vector_";
    private static String REFUSE_EXPRESSION = "_RefuseExpression_";
    private static String TREE = "_Tree_";
    private static String DELIMITER = "#";
    private static String NEW_LINE = "\n";

    @Override
    public String parseDtoToConfig(TableDTO tableDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getConst(tableDTO.getConstants()));
        stringBuilder.append(getVector(tableDTO.getVectors()));
        stringBuilder.append(getRefuseExpression(tableDTO.getRefuseExpression()));
        stringBuilder.append(getTree(tableDTO.getEvents()));

        return stringBuilder.toString().trim();
    }

    private StringBuilder getRefuseExpression(String refuseExpression) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REFUSE_EXPRESSION);
        stringBuilder.append("=");
        stringBuilder.append(DELIMITER);
        stringBuilder.append(refuseExpression);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(NEW_LINE);
        return stringBuilder;
    }

    private StringBuilder getConst(List<ConstantDTO> constant) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstantDTO c : constant) {
            stringBuilder.append(CONSTANT);
            stringBuilder.append("=");
            stringBuilder.append(c.getName());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(c.getValue());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(c.getInfo());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder;
    }

    private StringBuilder getVector(List<VectorDTO> vectorDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        for (VectorDTO v : vectorDTO) {
            stringBuilder.append(VECTOR);
            stringBuilder.append("=");
            stringBuilder.append(v.getName());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(v.getValue());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(v.getInfo());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder;
    }

    private StringBuilder getTree(List<EventDTO> eventDTOS) {
        StringBuilder stringBuilder = new StringBuilder();
        for (EventDTO e : eventDTOS) {
            stringBuilder.append(TREE);
            stringBuilder.append("=");
            stringBuilder.append(DELIMITER);
            stringBuilder.append(e.getCondition());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(e.getFormula());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(e.getAlternative());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(e.getEvent());
            stringBuilder.append(DELIMITER);
            stringBuilder.append(DELIMITER);
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder;
    }

    public static void main(String[] args) {
        ParserUiDtoToConfigJavaStyle parer = new ParserUiDtoToConfigJavaStyle();
        TableDTO tableDTO = new TableDTO();
        tableDTO.getConstants().add(new ConstantDTO("N","20","кількість основних модулів"));
        tableDTO.getConstants().add(new ConstantDTO("R","10","кількість запасних модулів"));
        tableDTO.getConstants().add(new ConstantDTO("L","0.065","ймовірність відмови основного модуля"));

        tableDTO.getVectors().add(new VectorDTO("V[0]","N","перший елем вктора"));
        tableDTO.getVectors().add(new VectorDTO("V[1]","R","перший елем вктора"));

        tableDTO.setRefuseExpression("V[0]<N");

        tableDTO.getEvents().add(new EventDTO("V[0]=V[0]-1;V[2]=V[2]+1;","(V[0]>0) && (V[1]==0)","V[0]*L","1","1","Vidmova"));
        tableDTO.getEvents().add(new EventDTO("V[1]=V[1]-1;V[2]=V[2]+1;","(V[0]>0) && (V[1]>0)","V[0]*L","1","1","Vidmova"));

        System.out.println(parer.parseDtoToConfig(tableDTO));
    }
}
