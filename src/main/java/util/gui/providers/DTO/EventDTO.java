package util.gui.providers.DTO;

public class EventDTO {
    private String event;
    private String condition;
    private String formula;
    private String alternative;
    private String mod;
    private String info;

    public EventDTO() {
    }

    public EventDTO(String event, String condition, String formula, String alternative, String mod, String info) {
        this.event = event;
        this.condition = condition;
        this.formula = formula;
        this.alternative = alternative;
        this.mod = mod;
        this.info = info;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
