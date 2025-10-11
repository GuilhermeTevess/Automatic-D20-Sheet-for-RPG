package models.entities;

import models.enums.AttributeType;
import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private int age;
    private String profession;
    private List<Attributes> attributes = new ArrayList<>();

    private int baseLife = 14;
    private int maxLife;
    private int currentLife;

    public Character(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;

        for (AttributeType type : AttributeType.values()) {
            Attributes attr = new Attributes(type.getDisplayName(), 10, 0);
            for (String skillName : type.getDefaultSkills()) {
                attr.addSkill(new Skills(skillName, 0, type.name()));
            }
            attributes.add(attr);
        }

        updateLife();
    }

    // Construtor vazio pro Gson
    public Character() {}

    // --- VIDA ---
    public void updateLife() {
        int constitution = getAttributeValue("Constituição");
        int bonus = calculateLifeBonus(constitution);
        this.maxLife = Math.max(baseLife + bonus, 1);
        if (currentLife == 0 || currentLife > maxLife) currentLife = maxLife;
    }

    private int calculateLifeBonus(int constitution) {
        if (constitution <= 5) return -4;
        if (constitution <= 12) return 4;
        if (constitution <= 16) return 8;
        if (constitution <= 19) return 16;
        if (constitution <= 23) return 32;
        if (constitution <= 28) return 38;
        if (constitution <= 34) return 44;
        if (constitution <= 38) return 50;
        return 60;
    }

    public int getAttributeValue(String name) {
        for (Attributes attr : attributes) {
            if (attr.getName().equalsIgnoreCase(name)) {
                return attr.getValue();
            }
        }
        return 0;
    }

    public List<Attributes> getAttributes() {
        return attributes;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = Math.min(currentLife, maxLife);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    public void rebuildAfterLoad() {
        for (Attributes attr : attributes) {
            attr.setMod((attr.getValue() - 10) / 2);
        }
        updateLife();
    }
}
