package com.example.myjavafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoggedInController {
    @FXML
    private Label labelWelcome;

    @FXML
    private Button btnLogout;

    private Stage stage;

    public void setUserInformation(String username){
        labelWelcome.setText("Welcome "+username+" !");
    }

    public void logoutOnAction(ActionEvent event) {
        try{
            Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage loginStage=new Stage();
            Scene scene=new Scene(root);
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(scene);
            loginStage.show();

            stage=(Stage) btnLogout.getScene().getWindow();
            stage.close();
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }

    public void closeBtn(ActionEvent event) {
        stage=(Stage) btnLogout.getScene().getWindow();
        stage.close();
    }
}
