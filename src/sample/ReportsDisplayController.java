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
import java.util.Optional;

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
    @FXML
    Button deleteButton = new Button();
    @FXML
    Button replyButton = new Button();

    private ObservableList<Report> reports;
    Stage stage = new Stage();
    Parent root;
    private static int reportID;
    private static int destination;

    public static int getDestination() {
        return destination;
    }

    public static int getReportID() {
        return reportID;
    }

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
                    report.setDestination(rs.getInt(DataSource.getColumnReportsDestination()));
                    if(report.getDestination() == 0) {
                        reports.add(report);
                    }
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
            reportID = report.getReportID();
            int userID = report.getUserID();
            destination = userID;
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

                rs.close();

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

    public void onDeleteButtonClicked(){

        Report report;
        reports =  FXCollections.observableArrayList();

        if(adminReportsTable.getSelectionModel().getSelectedItem() != null){

            report = adminReportsTable.getSelectionModel().getSelectedItem();

            int reportID = report.getReportID();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()){

                if(result.get() == ButtonType.OK){

                    DataSource.getInstance().deleteFromReports(reportID);

                    try {
                        ResultSet rs = DataSource.getInstance().queryReports();

                        if (rs != null) {

                            while (rs.next()) {

                                Report r = new Report();
                                r.setReportID(rs.getInt(DataSource.getColumnReportsId()));
                                r.setUserID(rs.getInt(DataSource.getColumnReportsUserId()));
                                r.setSubmissionDate(rs.getTimestamp(DataSource.getColumnReportSubmissionDate()));
                                r.setReportTitle(rs.getString(DataSource.getColumnReportsTitle()));
                                r.setReportText(rs.getString(DataSource.getColumnReportsText()));
                                r.setDestination(rs.getInt(DataSource.getColumnReportsDestination()));
                                if(r.getDestination()==0)
                                reports.add(r);
                            }

                            adminReportsTable.setItems(reports);
                            rs.close();
                            nameLabel.setText(null);
                            surnameLabel.setText(null);
                            dateLabel.setText(null);
                            emailLabel.setText(null);
                            adminReportWindow.setText(null);

                        } else {
                            alert.setTitle("Warning");
                            alert.setHeaderText("Error");
                            alert.showAndWait();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else{

                alert.setTitle("Warning");
                alert.setHeaderText("Error");
                alert.showAndWait();

            }

        }


    }



    public void onReplyButtonClicked(){

        if(adminReportsTable.getSelectionModel().getSelectedItem() != null) {

            try {
                Stage appStage = (Stage) outerAnchorPane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("AdminReplyScene.fxml"));
                appStage.setScene(new Scene(root));
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
