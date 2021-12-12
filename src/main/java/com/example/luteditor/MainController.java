package com.example.luteditor;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.commons.math3.util.Precision;

public class MainController {

    @FXML
    Stage stage;
    @FXML
    Scene scene;
    @FXML
    private TextField deadZoneTextField;
    @FXML
    private Slider deadZoneSlider;
    @FXML
    private TextField gainTextField;
    @FXML
    private Slider gainSlider;
    @FXML
    private TextField gammaTextField;
    @FXML
    private Slider gammaSlider;

    private static final String CALIBRATION_SCENE_FILE = "CalibrationScene.fxml";
    private static final String MAIN_EDITING_FILE = "MainEditingScene.fxml";
    DecimalFormat df = new DecimalFormat("###.##");

    // SCENE SWITCHING

    public void switchToNewCalibrationScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(CALIBRATION_SCENE_FILE));
        Node sourceNode = (Node)event.getSource();
        Scene sourceScene = sourceNode.getScene();
        stage = (Stage)sourceScene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainEditingScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_EDITING_FILE));
        Node sourceNode = (Node)event.getSource();
        Scene sourceScene = sourceNode.getScene();
        stage = (Stage)sourceScene.getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // MENU INTERACTION

    // dead zone
    public void updateDeadZoneFromTextField(Event event) throws  IOException{
        String stringValue = deadZoneTextField.getText();
        double value = Precision.round(Double.valueOf(stringValue),2);
        deadZoneSlider.adjustValue(value);
        // auto value cast
        deadZoneTextField.setText(""+Precision.round(deadZoneSlider.getValue(),2));
    }

    public void updateDeadZoneFieldFromSlider(Event event) throws IOException{
        double value = Precision.round(deadZoneSlider.getValue(),2);
        deadZoneTextField.setText(Double.toString(value));
    }

    // gain
    public void updateGainFromTextField(Event event) throws IOException{
        String stringValue = gainTextField.getText();
        long value = Math.round(Double.valueOf(stringValue));
        gainSlider.adjustValue(value);
        // auto value cast
        gainTextField.setText(""+Math.round(gainSlider.getValue()));
    }

    public void updateGainFieldFromSlider(Event event) throws IOException{
        long value = Math.round(gainSlider.getValue());
        gainTextField.setText(Long.toString(value));
    }

    // gamma
    public void updateGammaFromTextField(Event event) throws IOException{
        String stringValue = gammaTextField.getText();
        double value = Precision.round(Double.valueOf(stringValue),2);
        gammaSlider.adjustValue(value);
        gammaTextField.setText(""+Precision.round(gammaSlider.getValue(),2));
    }

    public void updateGammaFieldFromSlider(Event event) throws IOException{
        double value = Precision.round(gammaSlider.getValue(),2);
        gammaTextField.setText(Double.toString(value));
    }

    public void updateChart() {
        // TODO
    }

}