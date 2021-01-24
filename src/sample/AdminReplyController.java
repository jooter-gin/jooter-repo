package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AdminReplyController {


    @FXML
    Button backButton = new Button();
    @FXML
    Button logoutButton = new Button();
    @FXML
    TextArea textWindow1 = new TextArea();
    @FXML
    TextArea textWindow2 = new TextArea();
    @FXML
    Button sendButton = new Button();
    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();
    private String reportTitle;
    private int destination;


    public void initialize(){

        destination = ReportsDisplayController.getDestination();
        ResultSet rs = DataSource.getInstance().queryReportsByID(ReportsDisplayController.getReportID());
        if(rs != null){

            try {
                while(rs.next()) {

                    textWindow1.setText(rs.getString(DataSource.getColumnReportsText()));
                    reportTitle = rs.getString(DataSource.getColumnReportsTitle());
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }else{

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.showAndWait();

        }

        textWindow1.setWrapText(true);
        textWindow2.setWrapText(true);
        textWindow1.setEditable(false);

    }

    public void onSendButtonClicked(){

        Alert alert;
        String text = textWindow2.getText();
        if(text.trim().isEmpty() || text.isEmpty()){

            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Type something first");
            alert.showAndWait();

        }else{
            Report report = new Report();
            report.setReportText(text);
            report.setUserID(LoginController.getUserID());
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            report.setReportTitle(" re: " + reportTitle);
            report.setSubmissionDate(ts);
            report.setDestination(destination);
            DataSource.getInstance().insertIntoReports(report);
            textWindow2.clear();
            textWindow1.clear();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Message has been sent");
            alert.showAndWait();
        }



    }

    public void onLogOutButtonClicked(){

        try{

            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            outerAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onBackButtonClicked(){

        try{

            Parent root = FXMLLoader.load(getClass().getResource("adminReportsDisplay.fxml"));
            Stage stage = (Stage) outerAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException e){

            e.printStackTrace();
        }

    }


}
