package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    boolean isDisabled;
    private static int userID;


    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();
    @FXML
    AnchorPane innerAnchorPane = new AnchorPane();
    @FXML
    Label jooterLabel = new Label();
    @FXML
    ImageView scooterImage = new ImageView();
    @FXML
    Label loginLabelLogin = new Label();
    @FXML
    Button loginButton = new Button();
    @FXML
    Button createAccButton = new Button();
    @FXML
    PasswordField loginPasswordField = new PasswordField();
    @FXML
    TextField loginTextField = new TextField();
    @FXML
    Label loginLabelPassword = new Label();
    @FXML
    Label loginInvalidInfoLabel = new Label();


    public void onCreateAccButtonClicked(){
    try {

        Parent root = FXMLLoader.load(getClass().getResource("jooterRegistration.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        outerAnchorPane.getScene().getWindow().hide();

    }catch(IOException e){

        e.printStackTrace();
    }
    }

    public void initialize(){

        loginButton.setDisable(true);

    }

    public static int getUserID() {
        return userID;
    }

    public void onLoginButtonClicked(){

        String login = loginTextField.getText().trim();
        String password = loginPasswordField.getText().trim();
        int loginCheck = Login.CheckLogin(login, password);
        if(loginCheck !=0){

            Parent root;
            Stage stage = new Stage();

            if(loginCheck>0){

                try {
                    root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();
                    outerAnchorPane.getScene().getWindow().hide();
                    userID = Login.CheckLogin(login,password);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }else{

                try {
                    root = FXMLLoader.load(getClass().getResource("JooterAdminPanel.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();
                    outerAnchorPane.getScene().getWindow().hide();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }else{
            loginInvalidInfoLabel.setVisible(true);
            loginPasswordField.clear();
            loginTextField.clear();
            loginButton.setDisable(true);
        }

    }

    public void onTextFieldClicked(){

        loginInvalidInfoLabel.setVisible(false);

    }


    public void onKeyReleased(){

        String login = loginTextField.getText();
        String password = loginPasswordField.getText();
        isDisabled = ((login.isEmpty() || login.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty()));
        loginButton.setDisable(isDisabled);

    }




}
