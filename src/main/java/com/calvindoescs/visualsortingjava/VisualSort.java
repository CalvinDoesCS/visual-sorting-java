package com.calvindoescs.visualsortingjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class VisualSort extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("visualSortView.fxml")));
        Parent root = loader.load();
        VisualSortController visualSortController = loader.getController();
        Scene scene = new Scene(root, 1280, 720);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
        stage.setTitle("Visual Sorter!");
        stage.setScene(scene);
        stage.show();
        visualSortController.generateAndAddRectNodesOnScene();
        scene.widthProperty().addListener((observableValue, number, t1) -> {
            visualSortController.recalculateRectNodeSize();
        });
        scene.heightProperty().addListener((observableValue, number, t1) -> {
            visualSortController.recalculateRectNodeSize();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}