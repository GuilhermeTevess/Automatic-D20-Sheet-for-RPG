package models.entities;

public class Skills {
    private String name;
    private int value;
    private String type;

    public Skills(String name, int value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
