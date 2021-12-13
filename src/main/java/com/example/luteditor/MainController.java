package com.example.luteditor;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import model.Lut;
import org.apache.commons.math3.util.Precision;
import utils.Calculator;
import utils.LutWriter;

import javax.swing.*;

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
    @FXML
    private LineChart lineChart;

    private Lut lut;

    private static final String CALIBRATION_SCENE_FILE = "CalibrationScene.fxml";
    private static final String MAIN_EDITING_FILE = "MainEditingScene.fxml";
    private static final Integer LUT_RESOLUTION = 1000;

    // SCENE SWITCHING

    public void switchToNewCalibrationScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(CALIBRATION_SCENE_FILE)));
        Node sourceNode = (Node)event.getSource();
        Scene sourceScene = sourceNode.getScene();
        stage = (Stage)sourceScene.getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainEditingScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(MAIN_EDITING_FILE)));
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
        double value = Precision.round(Double.parseDouble(stringValue),2);
        deadZoneSlider.adjustValue(value);
        // auto value cast
        deadZoneTextField.setText(""+Precision.round(deadZoneSlider.getValue(),2));
        // update graph
        updateChart();
    }

    public void updateDeadZoneFieldFromSlider(Event event) throws IOException{
        double value = Precision.round(deadZoneSlider.getValue(),2);
        deadZoneTextField.setText(Double.toString(value));
        // update graph
        updateChart();
    }

    // gain
    public void updateGainFromTextField(Event event) throws IOException{
        String stringValue = gainTextField.getText();
        long value = Math.round(Double.parseDouble(stringValue));
        gainSlider.adjustValue(value);
        // auto value cast
        gainTextField.setText(""+Math.round(gainSlider.getValue()));
        // update graph
        updateChart();
    }

    public void updateGainFieldFromSlider(Event event) throws IOException{
        long value = Math.round(gainSlider.getValue());
        gainTextField.setText(Long.toString(value));
        // update graph
        updateChart();
    }

    // gamma
    public void updateGammaFromTextField(Event event) throws IOException{
        String stringValue = gammaTextField.getText();
        double value = Precision.round(Double.parseDouble(stringValue),2);
        gammaSlider.adjustValue(value);
        gammaTextField.setText(""+Precision.round(gammaSlider.getValue(),2));
        // update graph
        updateChart();
    }

    public void updateGammaFieldFromSlider(Event event) throws IOException{
        double value = Precision.round(gammaSlider.getValue(),2);
        gammaTextField.setText(Double.toString(value));
        // update graph
        updateChart();
    }

    // default values
    public void setDefaultValues(){
        deadZoneTextField.setText("0");
        deadZoneSlider.setValue(0);
        gainTextField.setText("100");
        gainSlider.setValue(100);
        gammaTextField.setText("0");
        gammaSlider.setValue(0);
        // update graph
        updateChart();
    }

    public void updateChart() {
        this.lut = utils.Calculator.generateCustomLut(
                LUT_RESOLUTION,
                Double.parseDouble(deadZoneTextField.getText()),
                Integer.parseInt(gainTextField.getText()),
                Double.parseDouble(gammaTextField.getText())
        );
        XYChart.Series lutSeries = Calculator.getSeriesFromLut(this.lut, Calculator.LUT_SERIES_KEY);
        lineChart.getData().clear();
        lineChart.getData().add(lutSeries);
    }

    public void generateLut(){
        updateChart();
        String fileName = LutWriter.generateFileName(
                Integer.parseInt(gainTextField.getText()),
                Double.parseDouble(deadZoneTextField.getText()),
                Double.parseDouble(gammaTextField.getText())
        );
        try {
            LutWriter.saveLutFile(
                    fileName,
                    this.lut);
            JOptionPane.showMessageDialog(null, "Process completed! Output file: '" + fileName + "'.");
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to write output file: '" + fileName + "'.");
        }
    }

}