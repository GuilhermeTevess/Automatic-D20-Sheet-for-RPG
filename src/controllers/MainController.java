package controllers;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.entities.Attributes;
import models.entities.Character;
import models.entities.Skills;
import models.utils.DiceRoller;

import java.io.FileReader;
import java.io.FileWriter;

public class MainController {

    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtCurrentLife;
    @FXML private TextField txtMaxLife;
    @FXML private ProgressBar lifeBar;
    @FXML private Label lifeLabel;
    @FXML private GridPane gridAttributes;
    @FXML private GridPane gridSkills;

    private Character hero;

    @FXML
    public void initialize() {
        hero = new Character("Novo", 18, "Investigador");

        // ATRIBUTOS
        int row = 0;
        for (Attributes attr : hero.getAttributes()) {
            Label name = new Label(attr.getName());
            TextField valueField = new TextField(String.valueOf(attr.getValue()));
            valueField.setPrefWidth(50);

            // Atualiza o atributo e recalcula modificadores
            valueField.textProperty().addListener((obs, old, val) -> {
                try {
                    int v = Integer.parseInt(val);
                    attr.setValue(v);
                    attr.setMod((v - 10) / 2);

                    // Se for Constituição, recalcula a vida
                    if (attr.getName().equalsIgnoreCase("Constituição")) {
                        hero.updateLife();
                        updateLifeDisplay();
                    }
                } catch (NumberFormatException ignored) {}
            });

            gridAttributes.addRow(row++, name, valueField);
        }

        // PERÍCIAS
        row = 0;
        int col = 0;
        for (Attributes attr : hero.getAttributes()) {
            for (Skills skill : attr.getSkillsList()) {
                createSkillBox(skill, row++, col);
                if (row > 6) { // quebra de coluna
                    row = 0;
                    col++;
                }
            }
        }

        // Escuta mudanças manuais na vida
        txtCurrentLife.textProperty().addListener((obs, old, val) -> updateLifeBar());
        txtMaxLife.textProperty().addListener((obs, old, val) -> updateLifeBar());

        updateLifeDisplay();
    }

    private void createSkillBox(Skills skill, int row, int col) {
        TextField valueField = new TextField(String.valueOf(skill.getValue()));
        valueField.setPrefWidth(50);

        Button btn = new Button(skill.getName());
        btn.setStyle("-fx-background-color: #2c2c2c; -fx-text-fill: white; -fx-font-weight: bold;");
        btn.setOnAction(e -> {
            try {
                skill.setValue(Integer.parseInt(valueField.getText()));
            } catch (NumberFormatException ignored) {}
            showRollResult(skill);
        });

        VBox skillBox = new VBox(5, btn, valueField);
        skillBox.setAlignment(Pos.CENTER);
        gridSkills.add(skillBox, col, row);
    }

    private void showRollResult(Skills skill) {
        Alert loading = new Alert(Alert.AlertType.INFORMATION);
        loading.setHeaderText(skill.getName());
        loading.setContentText("🎲 Rolando...");
        loading.show();

        new Thread(() -> {
            try {
                Thread.sleep(800);
            } catch (InterruptedException ignored) {}

            String resultado = DiceRoller.rollSkill(skill.getValue());

            Platform.runLater(() -> {
                loading.close();
                Alert result = new Alert(Alert.AlertType.INFORMATION);
                result.setTitle("Resultado da Rolagem");
                result.setHeaderText(skill.getName());
                result.setContentText(resultado);
                result.showAndWait();
            });
        }).start();
    }

    private void updateLifeDisplay() {
        hero.updateLife();
        txtMaxLife.setText(String.valueOf(hero.getMaxLife()));
        txtCurrentLife.setText(String.valueOf(hero.getCurrentLife()));
        updateLifeBar();
    }

    private void updateLifeBar() {
        try {
            int current = Integer.parseInt(txtCurrentLife.getText());
            int max = Integer.parseInt(txtMaxLife.getText());
            double ratio = Math.max(0, Math.min((double) current / max, 1));
            lifeBar.setProgress(ratio);
            lifeLabel.setText(current + " / " + max);
        } catch (Exception ignored) {}
    }

    @FXML
    public void onSaveClick() {
        try (FileWriter writer = new FileWriter("character.json")) {
            new Gson().toJson(hero, writer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ficha salva");
            alert.setHeaderText(null);
            alert.setContentText("Ficha salva em character.json!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onLoadClick() {
        try (FileReader reader = new FileReader("character.json")) {
            hero = new Gson().fromJson(reader, Character.class);
            hero.rebuildAfterLoad();
            updateLifeDisplay();
            showLoadedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoadedData() {
        txtName.setText(hero.getName());
        txtAge.setText(String.valueOf(hero.getAge()));
    }
}
