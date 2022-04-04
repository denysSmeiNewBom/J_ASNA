package util.graph.assistant.impl;

import util.graph.State;
import util.graph.assistant.BuilderAssistant;

public class BuilderAssistantV1 implements BuilderAssistant {
    private static final int POSITIVE = -1;
    private static final int NEGATIVE = 1;

    @Override
    public void addNewStateToGraph(State currentState, int[] vector, double intensive) {
        State potentialNewState = new State(vector);
        if (!State.getGraph().contains(potentialNewState)) {/*
            System.out.print(currentState.getVector()[0] + " ,");
            System.out.print(currentState.getVector()[1] + " ,");
            System.out.print(currentState.getVector()[2] + " ,");
            System.out.print(currentState.getVector()[3] + "\n");*/
            State.getGraph().add(potentialNewState);
        }
        changeIntensive(State.getGraph().indexOf(currentState), currentState, intensive, NEGATIVE);
        changeIntensive(State.getGraph().indexOf(potentialNewState), currentState, intensive, POSITIVE);
    }

    private void changeIntensive(int indexOfState, State state, double intensive, int sight) {
        intensive = intensive * sight;
        if (state.getIntensity().containsKey(indexOfState)) {
            double intensiveOfNextState = state.getIntensity().get(indexOfState);
            state.getIntensity().put(indexOfState, intensiveOfNextState + intensive);
        } else {
            state.getIntensity().put(indexOfState, intensive);
        }
    }
}
