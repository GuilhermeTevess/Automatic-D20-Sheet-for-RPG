package models.enums;

import java.util.Arrays;
import java.util.List;

public enum AttributeType {
    FORCA("Força", Arrays.asList("Atletismo")),
    DESTREZA("Destreza", Arrays.asList("Acrobacia", "Furtividade", "Prestidigitação")),
    INTELIGENCIA("Inteligência", Arrays.asList("Arcanismo", "Investigação", "História")),
    CONSTITUICAO("Constituição", Arrays.asList("Resistência", "Sobrevivência")),
    CARISMA("Carisma", Arrays.asList("Enganação", "Intimidação", "Persuasão", "Atuação")),
    MENTAL("Mental", Arrays.asList("Intuição", "Psicologia", "Força de Vontade"));

    private final String displayName;
    private final List<String> defaultSkills;

    AttributeType(String displayName, List<String> defaultSkills) {
        this.displayName = displayName;
        this.defaultSkills = defaultSkills;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getDefaultSkills() {
        return defaultSkills;
    }
}
