package com.example.myjavafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String username){
        Parent root=null;
        if (username!=null){
            try {
                FXMLLoader loader=new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root=loader.load();
                LoggedInController loggedInController=loader.getController();
                loggedInController.setUserInformation(username);

            }catch (IOException exception){
                exception.printStackTrace();
            }

        }else {
            try {
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
