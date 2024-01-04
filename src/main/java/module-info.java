module com.example.myjavafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires com.dlsc.formsfx;

    opens com.example.myjavafxapp to javafx.fxml;
    exports com.example.myjavafxapp;
}