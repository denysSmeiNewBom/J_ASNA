package util.gui.DTO;

import java.util.ArrayList;
import java.util.List;

public class TableDTO {
    private List<ConstantDTO> constants;
    private List<EventDTO> events;
    private List<VectorDTO> vectors;
    private String refuseExpression;

    public TableDTO() {
        this.constants = new ArrayList<>();
        this.events = new ArrayList<>();
        this.vectors = new ArrayList<>();
    }

    public boolean isEmpty() {
        return constants.isEmpty()
                || events.isEmpty()
                || vectors.isEmpty()
                || (refuseExpression == null || refuseExpression.equals(""));
    }

    public List<ConstantDTO> getConstants() {
        return constants;
    }

    public void setConstants(List<ConstantDTO> constants) {
        this.constants = constants;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public List<VectorDTO> getVectors() {
        return vectors;
    }

    public void setVectors(List<VectorDTO> vectors) {
        this.vectors = vectors;
    }

    public String getRefuseExpression() {
        return refuseExpression;
    }

    public void setRefuseExpression(String refuseExpression) {
        this.refuseExpression = refuseExpression;
    }
}
