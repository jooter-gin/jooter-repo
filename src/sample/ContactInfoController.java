package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactInfoController {

    @FXML
    Button backButton = new Button();
    @FXML
    Button logoutButton = new Button();
    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();

    public void onBackButtonClicked(){

        try {
            Stage stage = (Stage) outerAnchorPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onLogoutButtonClicked(){

        try{

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            outerAnchorPane.getScene().getWindow().hide();


        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
