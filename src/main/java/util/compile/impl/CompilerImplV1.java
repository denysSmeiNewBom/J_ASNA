package util.compile.impl;

import util.compile.Compiler;
import util.compile.core.RuntimeCompiler;
import util.graph.builder.GraphBuilder;
import util.parser.GraphBuilderParser;

public class CompilerImplV1 implements Compiler {
    private GraphBuilderParser graphBuilderParser;

    public CompilerImplV1(GraphBuilderParser graphBuilderParser) {
        this.graphBuilderParser = graphBuilderParser;
    }

    public GraphBuilder getBuilder(String className , String configuration){
        String simpleClassDeclaration =
                graphBuilderParser.getExtendedClass(className, configuration);
        RuntimeCompiler r = new RuntimeCompiler();
        r.addClass(className, simpleClassDeclaration);
        r.compile();
        Class clazz  = r.getCompiledClass("util.graph.builder.test." + className);
        GraphBuilder graphBuilder = null;
        try {
            graphBuilder = (GraphBuilder)clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return graphBuilder;
    }
}
