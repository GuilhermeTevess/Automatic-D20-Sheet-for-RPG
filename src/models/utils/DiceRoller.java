package models.utils;

import java.util.Random;

public class DiceRoller {

    private static final Random random = new Random();

    public static int rollD20() {
        return random.nextInt(20) + 1;
    }

    public static String rollSkill(int habilidade) {
        int roll = rollD20();

        int normal = Math.min(21 - habilidade, 20);
        int bom = Math.min(normal + 5, 20);
        int extremo = Math.min(normal + 8, 20);

        String resultado;
        if (roll >= extremo) {
            resultado = "Extremo";
        } else if (roll >= bom) {
            resultado = "Bom";
        } else if (roll >= normal) {
            resultado = "Normal";
        } else {
            resultado = "Fracasso";
        }

        return String.format("Rolou %d → %s (Habilidade: %d | N=%d, B=%d, E=%d)",
                roll, resultado, habilidade, normal, bom, extremo);
    }
}
