package com.example.luteditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML

    Stage stage;
    Scene scene;

    public void switchToNewCalibrationScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CalibrationScene.fxml"));
        Node sourceNode = (Node)event.getSource();
        Scene sourceScene = sourceNode.getScene();
        stage = (Stage)sourceScene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainEditingScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainEditingScene.fxml"));
        Node sourceNode = (Node)event.getSource();
        Scene sourceScene = sourceNode.getScene();
        stage = (Stage)sourceScene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}