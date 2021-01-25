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

public class UserRepliesController {

    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();
    @FXML
    TableView<Report> adminReportsTable = new TableView<>();
    @FXML
    TableColumn<Report,String> titleColumn = new TableColumn<>();
    @FXML
    TableColumn<Report,String> textColumn = new TableColumn<>();
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

    public void initialize(){

        adminReportWindow.setEditable(false);
        adminReportWindow.setWrapText(true);
        reports =  FXCollections.observableArrayList();
        try {
            ResultSet rs = DataSource.getInstance().queryReports();

            if(rs !=null) {

                while (rs.next()) {
                    Report report = new Report();
                    report.setReportTitle(rs.getString(DataSource.getColumnReportsTitle()));
                    report.setReportText(rs.getString(DataSource.getColumnReportsText()));
                    report.setDestination(rs.getInt(DataSource.getColumnReportsDestination()));
                    if(report.getDestination() == LoginController.getUserID()) {
                        reports.add(report);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        adminReportsTable.setItems(reports);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("reportTitle"));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("reportText"));


    }


    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
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

