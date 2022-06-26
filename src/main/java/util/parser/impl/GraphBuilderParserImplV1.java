package util.parser.impl;

import util.parser.GraphBuilderParser;

public class GraphBuilderParserImplV1 implements GraphBuilderParser {
    private static final String DELIMITER = "#";
    private static final String JAVA_DELIMITER = ";";
    private static final String CONST = "_Const_";
    private static final String VECTOR = "_Vector_";
    private static final String REFUSE_EXPRESSION = "_RefuseExpression_";
    private static final String TREE = "_Tree_";
    private static final String V = "V";
    private static final String COMMENT_MARKER = "######";

    @Override
    public String getExtendedClass(String className, String conditions) {
        StringBuilder classDeclaration = new StringBuilder();
        classDeclaration.append("package util.graph.builder.test;\n\n" +
                "import util.graph.State;\n" +
                "import util.graph.assistant.impl.BuilderAssistantV1;\n" +
                "import util.graph.builder.GraphBuilder;\n" +
                "import java.util.List;\n\n");
        classDeclaration.append("public class ");
        classDeclaration.append(className);
        classDeclaration.append(" extends GraphBuilder {\n");
        addConst(conditions, classDeclaration);
        conditions = conditions.substring(conditions.indexOf(VECTOR));
        classDeclaration.append("    public " + className + "() {\n"
                + "        super(new int[]{");
        addVector(conditions, classDeclaration);
        conditions = conditions.substring(conditions.indexOf(REFUSE_EXPRESSION));
        classDeclaration.append("}, new BuilderAssistantV1());\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<State> getWholeGraph() {\n" +
                "        int[] vec;\n" +
                "        int[] v;\n" +
                "        double intensive;\n" +
                "        int i = 0;\n" +
                "        State.getGraph().add(this.state);\n" +
                "        while (i < State.getGraph().size()) {\n" +
                "            State workingState = State.getGraph().get(i);\n" +
                "            vec = workingState.getVector();\n" +
                "            if (");
        addRefuseExpression(conditions, classDeclaration);
        conditions = conditions.substring(conditions.indexOf(TREE));
        classDeclaration.append(") {\n" +
                "                i++;\n" +
                "                continue;\n" +
                "            }\n");
        addTransitionConditions(conditions, classDeclaration);
        classDeclaration.append("            i++;\n" +
                "        }\n" +
                "        return State.getGraph();\n" +
                "    }\n");
        classDeclaration.append("}\n");

        return classDeclaration.toString();
    }

    private static void addTransitionConditions(String conditions,
                                                StringBuilder classDeclaration) {
        conditions = conditions.replace("_Tree_=#", "");
        String[] arrOfConditions = conditions.split("\n");
        for (String condition : arrOfConditions) {
            if (condition.contains(COMMENT_MARKER)) {
                continue;
            }
            String conditionByItSelf = condition.substring(0,
                    condition.indexOf(DELIMITER)).replace(V, "vec");
            condition = condition.substring(condition.indexOf(DELIMITER) + 1);
            String intensive = condition.substring(0,
                    condition.indexOf(DELIMITER)).replace(V, "vec");
            condition = condition.substring(condition.indexOf(DELIMITER) + 1);
            classDeclaration.append("            if (");
            classDeclaration.append(conditionByItSelf);
            classDeclaration.append(") {\n" +
                    "                intensive = ");
            classDeclaration.append(intensive);
            classDeclaration.append(";\n" +
                    "                v = vec.clone();\n");
            // 1
            condition = condition.substring(2);
            // 1
            String vectorModification;
            while (condition.contains(JAVA_DELIMITER)) {
                classDeclaration.append("                ");
                vectorModification = condition.substring(0,
                        condition.indexOf(JAVA_DELIMITER) + 1).replace(V, "v");
                classDeclaration.append(vectorModification);
                classDeclaration.append("\n");
                condition = condition.substring(condition.indexOf(JAVA_DELIMITER) + 1);
            }
            classDeclaration.append("                builderAssistant"
                    + ".addNewStateToGraph(workingState, v, intensive);\n"
                    + "            }\n");
        }
    }

    private static void addRefuseExpression(String conditions,
                                            StringBuilder stringBuilder) {
        conditions = conditions.substring(0, conditions.indexOf(TREE));
        String[] arrOfRefuseExpression = conditions.split("\n");
        String refuseExpression;
        for (String s : arrOfRefuseExpression) {
            s = s.substring(s.indexOf(DELIMITER) + 1);
            refuseExpression = s.substring(0,
                    s.indexOf(DELIMITER)).replace(V, "vec");
            stringBuilder.append(refuseExpression);
            stringBuilder.append(" || ");
        }
        stringBuilder.append("false");
    }

    private static void addVector(String conditions, StringBuilder stringBuilder) {
        conditions = conditions.substring(0, conditions.indexOf(REFUSE_EXPRESSION));
        String[] arrOfVector = conditions.split("\n");
        String value;
        for (String s : arrOfVector) {
            s = s.substring(s.indexOf(DELIMITER) + 1);
            value = s.substring(0, s.indexOf(DELIMITER));
            stringBuilder.append(value);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }

    private static void addConst(String conditions, StringBuilder stringBuilder) {
        conditions = conditions.substring(0, conditions.indexOf(VECTOR));
        String[] arrOfCondition = conditions.split("\n");
        String nameOfConstant;
        String valueOfVariable;
        String typeOfVariable;
        for (String s : arrOfCondition) {
            nameOfConstant = s.substring(s.indexOf(CONST)
                    + CONST.length() + 1, s.indexOf(DELIMITER));
            s = s.substring(s.indexOf(DELIMITER) + 1);
            valueOfVariable = s.substring(0, s.indexOf(DELIMITER));
            typeOfVariable = valueOfVariable.contains(".") ? "double" : "int";
            stringBuilder.append("    private static ");
            stringBuilder.append(typeOfVariable);
            stringBuilder.append(" ");
            stringBuilder.append(nameOfConstant);
            stringBuilder.append(" = ");
            stringBuilder.append(valueOfVariable);
            stringBuilder.append(";\n");
        }
        stringBuilder.append("\n");
    }

    /*public static void main(String[] args) {
        GraphBuilderParserImplV1 graphBuilderParserImplV1 = new GraphBuilderParserImplV1();
        System.out.println(graphBuilderParserImplV1.getExtendedClass("Zero",
                "_Const_=L#0.065#ймовірність відмови основного модуля#\n" +
                        "_Const_=N#20#кількість основних елементів#\n" +
                        "_Const_=R#20#кількість запасних елементів#\n" +
                        "_Const_=M#0#кільскість неробочих елементів#\n" +
                        "_Const_=Lr#0.03#ймовірність відмови робочого елемента#\n" +
                        "_Const_=KR#15#кількість рем-наборів#\n" +
                        "_Const_=TRap#0.9#час відновлення елемента#\n" +
                        "_Vector_=V[0]#N#перший елем вктора#\n" +
                        "_Vector_=V[1]#R#другий елем вктора#\n" +
                        "_Vector_=V[2]#M#третій елем вктора#\n" +
                        "_Vector_=V[3]#KR#четвертий елем вктора#\n" +
                        "_RefuseExpression_=#V[0]<N##\n" +
                        "_Tree_=Vidmova######\n" +
                        "_Tree_=#(V[0]>0) && (V[1]==0)#V[0]*L#1#V[0]=V[0]-1;V[2]=V[2]+1;##\n" +
                        "_Tree_=#(V[0]>0) && (V[1]>0)#V[0]*L#1#V[1]=V[1]-1;V[2]=V[2]+1;##\n" +
                        "_Tree_=Vidnovlennja######\n" +
                        "_Tree_=#(V[2]>0) && (V[3]>0)#V[2]*TRap#1#V[2]=V[2]-1;V[1]=V[1]+1;V[3]=V[3]-1;##\n" +
                        "_Tree_=Vidmova rezervu######\n" +
                        "_Tree_=#V[1]>0#V[1]*Lr#1#V[1]=V[1]-1;V[2]=V[2]+1;##\n"));
    }*/

    public static void main(String[] args) {
        GraphBuilderParserImplV1 graphBuilderParserImplV1 = new GraphBuilderParserImplV1();
        System.out.println(graphBuilderParserImplV1.getExtendedClass("Zero",
                "_Const_=N#20#кількість основних модулів#\n" +
                        "_Const_=R#10#кількість запасних модулів#\n" +
                        "_Const_=L#0.065#ймовірність відмови основного модуля#\n" +
                        "_Vector_=V[0]#N#перший елем вктора#\n" +
                        "_Vector_=V[1]#R#перший елем вктора#\n" +
                        "_RefuseExpression_=#V[0]<N##\n" +
                        "_Tree_=#(V[0]>0) && (V[1]==0)#V[0]*L#1#V[0]=V[0]-1;V[2]=V[2]+1;##\n" +
                        "_Tree_=#(V[0]>0) && (V[1]>0)#V[0]*L#1#V[1]=V[1]-1;V[2]=V[2]+1;##\n"));
    }
}
