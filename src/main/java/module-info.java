module com.example.luteditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.luteditor to javafx.fxml;
    exports com.example.luteditor;
}