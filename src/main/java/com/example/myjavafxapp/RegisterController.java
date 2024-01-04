package com.example.myjavafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private ImageView shieldImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File shieldFile=new File("C:\\Users\\u\\IdeaProjects\\MyJavaFxApp\\src\\verified_shield.png");
        Image shieldImage=new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    public void registerButtonOnAction(ActionEvent e){
        if (!setPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordLabel.setText("Password does not match");
        } else{
            registerUser();
            confirmPasswordLabel.setText("");
        }
    }

    public void closeButtonOnAction(ActionEvent e){
        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();

        String firstname=firstnameTextField.getText();
        String lastname=lastnameTextField.getText();
        String username=usernameTextField.getText();
        String password=setPasswordField.getText();

        if(firstnameTextField.getText().isBlank()==false&&lastnameTextField.getText().isBlank()==false&&
            usernameTextField.getText().isBlank()==false){
            String insertFields = "INSERT INTO user_account(firstname, lastname, username, password) VALUES (?, ?, ?,?)";
            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(insertFields);
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4,password);
                preparedStatement.executeUpdate();
                registrationMessageLabel.setTextFill(Color.SKYBLUE);
                registrationMessageLabel.setText("User has been registered successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            registrationMessageLabel.setTextFill(Color.RED);
            registrationMessageLabel.setText("You cannot register until you fill in all the fields!");
        }
    }
}
