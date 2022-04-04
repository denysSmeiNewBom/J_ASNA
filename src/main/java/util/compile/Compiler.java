package util.compile;

import util.graph.builder.GraphBuilder;

public interface Compiler {
    public GraphBuilder getBuilder(String className , String configuration);
}
