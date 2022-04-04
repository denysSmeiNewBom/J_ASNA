package util.graph.builder;

import util.graph.State;
import util.graph.assistant.BuilderAssistant;

import java.util.List;

public abstract class GraphBuilder {
    protected State state;
    protected BuilderAssistant builderAssistant;

    public GraphBuilder(int[] startCondition, BuilderAssistant builderAssistant) {
        this.state = new State(startCondition);
        this.builderAssistant = builderAssistant;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public BuilderAssistant getBuilderAssistant() {
        return builderAssistant;
    }

    public void setBuilderAssistant(BuilderAssistant builderAssistant) {
        this.builderAssistant = builderAssistant;
    }

    public abstract List<State> getWholeGraph();
}
