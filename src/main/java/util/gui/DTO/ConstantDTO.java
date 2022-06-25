package util.gui.DTO;

public class ConstantDTO {
    private String name;
    private String value;
    private String info;

    public ConstantDTO() {
    }

    public ConstantDTO(String name, String value, String info) {
        this.name = name;
        this.value = value;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
