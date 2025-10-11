package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        Scene scene = new Scene(loader.load());

        // adiciona o estilo visual
        scene.getStylesheets().add(getClass().getResource("/views/style.css").toExternalForm());
        System.out.println(getClass().getResource("/views/style.css"));

        stage.setTitle("Ficha de Investigador 🎲");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
