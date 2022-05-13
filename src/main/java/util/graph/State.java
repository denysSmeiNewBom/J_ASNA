package util.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class State {
    private static List<State> graph = new ArrayList<>();
    private Map<Integer,Double> intensity;
    private int[] vector;

    public State(int[] vector) {
        this.vector = vector;
        this.intensity = new HashMap<>();
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }

    public static List<State> getGraph() {
        return graph;
    }

    public static void setGraph(List<State> allGraphs) {
        State.graph = allGraphs;
    }

    public Map<Integer, Double> getIntensity() {
        return intensity;
    }

    public void setIntensity(Map<Integer, Double> intensity) {
        this.intensity = intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(vector, state.vector);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(intensity);
        result = 31 * result + Arrays.hashCode(vector);
        return result;
    }
}
