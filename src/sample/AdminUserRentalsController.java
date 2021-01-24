package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AdminUserRentalsController {

    private ObservableList<AdminHist> data = FXCollections.observableArrayList();
    Stage stage = new Stage();
    Parent root;
    @FXML
    AnchorPane outerAnchorPane = new AnchorPane();
    @FXML
    TableView<AdminHist> tableView = new TableView<>();
    @FXML
    TableColumn<AdminHist,String> c1 = new TableColumn<>();
    @FXML
    TableColumn<AdminHist,String> c2 = new TableColumn<>();
    @FXML
    TableColumn<AdminHist,Integer> c3 = new TableColumn<>();
    @FXML
    TableColumn<AdminHist, Timestamp> c4 = new TableColumn<>();
    @FXML
    TableColumn<AdminHist,Timestamp> c5 = new TableColumn<>();
    @FXML
    Button backButton = new Button();
    @FXML
    Button logoutButton = new Button();



   public void initialize(){

       ResultSet rs1 = DataSource.getInstance().queryAllFromRhistory();

       if(rs1 != null){

           try {

               while (rs1.next()) {

                   AdminHist adminHist = new AdminHist();
                   adminHist.setUserID(rs1.getInt(DataSource.getColumnRentsIduser()));
                   adminHist.setScooterID(rs1.getInt(DataSource.getColumnScooterID()));
                   adminHist.setRentalDate(rs1.getTimestamp(DataSource.getColumnRentsTimestamp()));
                   adminHist.setReturnDate(rs1.getTimestamp(DataSource.getColumnRentsReturnDate()));
                   adminHist.setUserName(rs1.getString(DataSource.getColumnUserName()));
                   adminHist.setUserSurname(rs1.getString(DataSource.getColumnUserSurname()));
                   data.add(adminHist);
               }

               rs1.close();

           }catch (SQLException e){
               e.printStackTrace();
           }
       }

   tableView.setItems(data);
   c1.setCellValueFactory(new PropertyValueFactory<AdminHist,String>("userName"));
   c2.setCellValueFactory(new PropertyValueFactory<AdminHist,String>("userSurname"));
   c3.setCellValueFactory(new PropertyValueFactory<AdminHist,Integer>("scooterID"));
   c4.setCellValueFactory(new PropertyValueFactory<AdminHist,Timestamp>("rentalDate"));
   c5.setCellValueFactory(new PropertyValueFactory<AdminHist,Timestamp>("returnDate"));


   }

   public void onBackButtonClicked(){

       try{
           root = FXMLLoader.load(getClass().getResource("JooterAdminPanel.fxml"));
           stage = (Stage) outerAnchorPane.getScene().getWindow();
           stage.setScene(new Scene(root));
           stage.show();


       }catch (IOException e){
           e.printStackTrace();
       }

   }

   public void onLogoutButtonClicked(){

       try {
           root = FXMLLoader.load(getClass().getResource("JooterLogin.fxml"));
           stage.setScene(new Scene(root));
           stage.show();
           outerAnchorPane.getScene().getWindow().hide();
       }catch (IOException e){
           e.printStackTrace();
       }
   }


}
