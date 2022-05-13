package util.graph.builder.test;

import util.graph.assistant.impl.BuilderAssistantV1;
import util.graph.builder.GraphBuilder;
import util.graph.State;

import java.util.List;

public class GraphTest extends GraphBuilder {
    private int N = 2;
    private int R = 1;
    private int M = 0;
    private int KR = 1;
    private double L = 0.1;
    private double Lr = 0.01;
    private double TRap = 1d/30d;

    public GraphTest() {
        super(new int[]{5, 4, 0, 4}, new BuilderAssistantV1());
    }

    @Override
    public List<State> getWholeGraph() {
        int[] vec;
        int[] v;
        double intensive;
        int i = 0;
        State.getGraph().add(this.state);
        while (i < State.getGraph().size()) {
            State workingState = State.getGraph().get(i);
            vec = workingState.getVector();
            if (vec[0] < N) {
                i++;
                continue;
            }
            if (vec[0] > 0 && vec[1] > 0) {
                intensive = vec[0] * L;
                v = vec.clone();
                v[1] = v[1] - 1;
                v[2] = v[2] + 1;
                builderAssistant.addNewStateToGraph(workingState, v, intensive);
            }
            if (vec[0] > 0 && vec[1] == 0) {
                intensive = vec[0] * L;
                v = vec.clone();
                v[0] = v[0] - 1;
                v[2] = v[2] + 1;
                builderAssistant.addNewStateToGraph(workingState, v, intensive);
            }
            if (vec[1] > 0){
                intensive = vec[1] * Lr;
                v = vec.clone();
                v[1] = v[1] - 1;
                v[2] = v[2] + 1;
                builderAssistant.addNewStateToGraph(workingState, v, intensive);
            }
            if ((vec[2] > 0) && (vec[3] > 0)){
                intensive = TRap;
                v = vec.clone();
                v[2] = v[2] - 1;
                v[3] = v[3] - 1;
                v[1] = v[1] + 1;
                builderAssistant.addNewStateToGraph(workingState, v, intensive);
            }
            i++;
        }
        return State.getGraph();
    }

    public static void main(String[] args) {
        GraphTest graphTest = new GraphTest();
        List<State> graph = graphTest.getWholeGraph();
        System.out.println(">");
    }
}
