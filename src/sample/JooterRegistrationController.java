package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class JooterRegistrationController {

    boolean isDisabled;

    @FXML
    AnchorPane regOuterAnchorPane = new AnchorPane();
    @FXML
    AnchorPane regInnerAnchorPane = new AnchorPane();
    @FXML
    Label regJooterLabel = new Label();
    @FXML
    TextField regNameField = new TextField();
    @FXML
    TextField regSurnameField = new TextField();
    @FXML
    TextField regLoginField = new TextField();
    @FXML
    PasswordField regPasswordField = new PasswordField();
    @FXML
    Label regNameLabel = new Label();
    @FXML
    Label regSurnameLabel = new Label();
    @FXML
    Label regLoginLabel = new Label();
    @FXML
    Label regEmailLabel = new Label();
    @FXML
    TextField regEmailField = new TextField();
    @FXML
    Label regPasswordLabel = new Label();
    @FXML
    Button regSubmitButton = new Button();
    @FXML
    Label userExistsLabel = new Label();
    @FXML
    Button regLoginButton = new Button();
    @FXML
    Label regInvalidLoginLabel = new Label();
    @FXML
    Label regInvalidEmailLabel = new Label();
    @FXML
    Label regInvalidPasswordLabel = new Label();


    public void initialize(){

        regSubmitButton.setDisable(true);
    }


    public void onTextFieldClicked(){

        regInvalidLoginLabel.setVisible(false);
        regInvalidPasswordLabel.setVisible(false);
        regInvalidEmailLabel.setVisible(false);

    }

    public void onKeyReleased(){

        String name = regNameField.getText();
        String surname = regSurnameField.getText();
        String login = regLoginField.getText();
        String email = regEmailField.getText();
        String password = regPasswordField.getText();
        isDisabled = ((name.isEmpty() || name.trim().isEmpty()) || (surname.isEmpty() || surname.trim().isEmpty()) || (login.isEmpty() || login.trim().isEmpty()) || (email.isEmpty() || email.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty()));
        regSubmitButton.setDisable(isDisabled);

    }


    public void onSubmitButtonClicked() {

        User user = new User();
        user.setUserName(regNameField.getText().trim());
        user.setUserSurname(regSurnameField.getText().trim());
        user.setUserLogin(regLoginField.getText().trim());
        user.setUserPassword(regPasswordField.getText().trim());
        user.setUserEmail(regEmailField.getText().trim());
        user.setUserCardNo("1111111111111111");
        user.setUserAccountBalance(0.0);
        userExistsLabel.setVisible(false);

        boolean isLoginValid = user.validateUserLogin(regLoginField.getText().trim());
        boolean isEmailValid = user.validateEmail(regEmailField.getText().trim());
        boolean isPasswordValid = user.validateUserPassword(regPasswordField.getText().trim());
        boolean isRegistered = Registration.checkIfUserExists(user.getUserLogin(), user.getUserEmail());

        if (!isLoginValid) {

            regSubmitButton.setDisable(true);
            regInvalidLoginLabel.setVisible(true);
            regLoginField.clear();

        } else {

            regInvalidLoginLabel.setVisible(false);
        }
        if (!isEmailValid) {

            regInvalidEmailLabel.setVisible(true);
            regSubmitButton.setDisable(true);
            regEmailField.clear();

        } else {

            regInvalidEmailLabel.setVisible(false);
        }

        if (!isPasswordValid) {

            regPasswordField.clear();
            regInvalidPasswordLabel.setVisible(true);
            regSubmitButton.setDisable(true);



        } else {

            regInvalidPasswordLabel.setVisible(false);
        }

        if (isLoginValid && isEmailValid && isPasswordValid) {

            if (!isRegistered) {

                Registration.registerUser(user);
                userExistsLabel.setText("Registration complete");
                userExistsLabel.setTextFill(Color.GREEN);
                userExistsLabel.setVisible(true);
                regSubmitButton.setDisable(true);

            } else {
                userExistsLabel.setText("User already exists");
                userExistsLabel.setTextFill(Color.RED);
                regSubmitButton.setDisable(true);
                userExistsLabel.setVisible(true);

            }

            regNameField.clear();
            regEmailField.clear();
            regSurnameField.clear();
            regPasswordField.clear();
            regNameField.clear();
            regSurnameField.clear();
            regLoginField.clear();
        }
    }

    public void onLoginButtonClicked(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            regOuterAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}
