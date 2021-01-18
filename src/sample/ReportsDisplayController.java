package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReportsDisplayController {

    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();
    @FXML
    TableView<Report> adminReportsTable = new TableView<>();
    @FXML
    TableColumn<Report,String> reportsColumn = new TableColumn<>();
    @FXML
    TextArea adminReportWindow = new TextArea();
    @FXML
    Label nameLabel = new Label();
    @FXML
    Label surnameLabel = new Label();
    @FXML
    Label emailLabel = new Label();
    @FXML
    Label dateLabel = new Label();
    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();

    private ObservableList<Report> reports;
    Stage stage = new Stage();
    Parent root;


    public void initialize(){

        adminReportWindow.setEditable(false);
        adminReportWindow.setWrapText(true);
        reports =  FXCollections.observableArrayList();

        try {
            ResultSet rs = DataSource.getInstance().queryReports();

            if(rs !=null) {

                while (rs.next()) {

                    Report report = new Report();
                    report.setReportID(rs.getInt(DataSource.getColumnReportsId()));
                    report.setUserID(rs.getInt(DataSource.getColumnReportsUserId()));
                    report.setSubmissionDate(rs.getTimestamp(DataSource.getColumnReportSubmissionDate()));
                    report.setReportTitle(rs.getString(DataSource.getColumnReportsTitle()));
                    report.setReportText(rs.getString(DataSource.getColumnReportsText()));
                    reports.add(report);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        adminReportsTable.setItems(reports);
        reportsColumn.setCellValueFactory(new PropertyValueFactory<>("reportTitle"));


    }

    public void onReportClicked(){

        Report report;

        if(adminReportsTable.getSelectionModel().getSelectedItem() != null){
             report = adminReportsTable.getSelectionModel().getSelectedItem();
             int userID = report.getUserID();
             String reportText = report.getReportText();
             adminReportWindow.setText(reportText);
             Timestamp submissionDate = report.getSubmissionDate();
             DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm");
             String formattedSubmissionDate = df.format(submissionDate);
             dateLabel.setText(formattedSubmissionDate);

             try{
                 ResultSet rs = DataSource.getInstance().queryUser(userID);
                 while(rs.next()){

                     nameLabel.setText(rs.getString(DataSource.getColumnUserName()));
                     surnameLabel.setText(rs.getString(DataSource.getColumnUserSurname()));
                     emailLabel.setText(rs.getString(DataSource.getColumnUserEmail()));


                 }
             }catch(SQLException e){
                 e.printStackTrace();
             }
        }

    }

    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("JooterAdminPanel.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            outerAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onLogOutButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            outerAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}