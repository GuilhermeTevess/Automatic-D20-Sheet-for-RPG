package models.entities;

import java.util.ArrayList;
import java.util.List;

public class Attributes {
    private String name;
    private int value;
    private int mod;
    private List<Skills> skillsList = new ArrayList<>();

    public Attributes(String name, int value, int mod) {
        this.name = name;
        this.value = value;
        this.mod = mod;
    }

    public void addSkill(Skills skill) {
        skillsList.add(skill);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getMod() {
        return mod;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public List<Skills> getSkillsList() {
        return skillsList;
    }
}
