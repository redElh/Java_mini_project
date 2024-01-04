package com.example.myjavafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    private Stage stage;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView loginImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Label labelOnError;

    @FXML
    private Button btnRegister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File loginFile=new File("C:\\Users\\u\\IdeaProjects\\MyJavaFxApp\\src\\LOGIN.png");
        Image loginImage=new Image(loginFile.toURI().toString());
        loginImageView.setImage(loginImage);

        File lockFile=new File("C:\\Users\\u\\IdeaProjects\\MyJavaFxApp\\src\\LOCK-removebg-preview.png");
        Image lockImage=new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }
    public void loginButtonOnAction(ActionEvent e){
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            validateLogin(e);
        } else {
            loginMessageLabel.setText("Fill in username and password");
        }

    }
    public void cancelButtonOnAction(ActionEvent e){
        stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin(ActionEvent e){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();

        String verifyLogin="SELECT count(1) FROM user_account WHERE username='"+
                usernameTextField.getText()+"' AND password='"+enterPasswordField.getText()+"'";

        try{
            Statement stm=connectDB.createStatement();
            ResultSet rs=stm.executeQuery(verifyLogin);

            while (rs.next()){
                if (rs.getInt(1)==1){
                    //loginMessageLabel.setTextFill(Color.SKYBLUE);
                    redirectToLoggedIn(e);

                } else {
                    loginMessageLabel.setText("Invalid login. Please try again.");
                    labelOnError.setText("Not a User?");
                    btnRegister.setText("Register");
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void redirectToRegister(){
        try{
            Parent root= FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage=new Stage();
            Scene scene=new Scene(root);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }

    public void redirectToLoggedIn(ActionEvent e){
            DBUtils.changeScene(e,"loggedIn.fxml",usernameTextField.getText());
            /*Parent root= FXMLLoader.load(getClass().getResource("loggedIn.fxml"));
            Stage loggedInStage=new Stage();
            Scene scene=new Scene(root);
            loggedInStage.initStyle(StageStyle.UNDECORATED);
            loggedInStage.setScene(scene);
            loggedInStage.show();*/

    }


}
