package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

public class ReportsController {


    @FXML
    TextArea reportSceneTextArea = new TextArea();
    @FXML
    AnchorPane reportSceneAnchorPane = new AnchorPane();
    @FXML
    Button submitButton = new Button();
    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();
    @FXML
    TextField titleTextField = new TextField();

    public void initialize(){

        reportSceneTextArea.setWrapText(true);

    }

    public void onSubmitButtonClicked(){

        String text = reportSceneTextArea.getText().trim();
        if(!text.isEmpty()){

            Report report = new Report();
            if(titleTextField.getText().trim().isEmpty()){
                report.setReportTitle("No title");
            }else{
                report.setReportTitle(titleTextField.getText().trim());
            }


            report.setUserID(LoginController.getUserID());
            report.setSubmissionDate(new Timestamp(System.currentTimeMillis()));
            report.setReportText(text);
            report.setDestination(0);
            DataSource.getInstance().insertIntoReports(report);
            reportSceneTextArea.clear();
            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Message sent");
            alert.showAndWait();


        }else{

            Alert alert  = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("No message to send. Please insert a message.");
            alert.showAndWait();
        }
        titleTextField.clear();

    }

    public void onLogoutButtonClicked(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            reportSceneAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onBackButtonClicked(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            reportSceneAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}


