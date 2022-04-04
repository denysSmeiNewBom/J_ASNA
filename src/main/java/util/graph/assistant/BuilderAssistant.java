package util.graph.assistant;

import util.graph.State;

public interface BuilderAssistant {
    void addNewStateToGraph(State currentState, int[] vector, double intensive);
}
