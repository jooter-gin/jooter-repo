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

public class UserHistorySceneController {

    @FXML
    AnchorPane UserHistAnorchPane = new AnchorPane();
    @FXML
    TableView<ScooterJoin> UserHistTable1 = new TableView<>();
    @FXML
    TableColumn<ScooterJoin,String> UserHistModel1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserHistMaxVelocity1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,String> UserHistColor1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserHistBasket1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserHistRange1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Double> UserHistPrice1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserHistBattery1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin, Timestamp> UserHistDate1 = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Timestamp> returnDateColumn1 = new TableColumn<>();
    @FXML
    Button UserHistBackButton = new Button();
    @FXML
    TableColumn<ScooterJoin,Double> costColumn = new TableColumn<>();
    @FXML
    Button UserHistLogoutButton = new Button();

    private static ObservableList<ScooterJoin> scooterHistoryData;

    public void initialize(){
        scooterHistoryData = FXCollections.observableArrayList();
        try{


            ResultSet rs = DataSource.getInstance().queryRHistory(LoginController.getUserID());
            while(rs.next()){

                ScooterJoin s = new ScooterJoin();
                s.setScooterModel(rs.getString(DataSource.getColumnScooterModel()));
                s.setScooterMaxVelocity(rs.getInt(DataSource.getColumnScooterMaxVelocity()));
                s.setScooterColor(rs.getString(DataSource.getColumnScooterColor()));
                s.setScooterBasket(rs.getInt(DataSource.getColumnScooterBasket()));
                s.setScooterRange(rs.getInt(DataSource.getColumnScooterRange()));
                s.setScooterPrice(rs.getDouble(DataSource.getColumnScooterPrice()));
                s.setScooterBattery(rs.getInt(DataSource.getColumnScooterBattery()));
                s.setRentalTime(rs.getTimestamp(DataSource.getColumnRentsTimestamp()));
                s.setReturnDate(rs.getTimestamp(DataSource.getColumnRentsReturnDate()));
                s.setUserID(rs.getInt(DataSource.getColumnRentsIduser()));
                s.setBalance(rs.getDouble(DataSource.getColumnRentsBalance()));
                scooterHistoryData.add(s);


                UserHistTable1.setItems(scooterHistoryData);
                UserHistModel1.setCellValueFactory(new PropertyValueFactory<>("scooterModel"));
                UserHistMaxVelocity1.setCellValueFactory(new PropertyValueFactory<>("scooterMaxVelocity"));
                UserHistColor1.setCellValueFactory(new PropertyValueFactory<>("scooterColor"));
                UserHistBasket1.setCellValueFactory(new PropertyValueFactory<>("scooterBasket"));
                UserHistRange1.setCellValueFactory(new PropertyValueFactory<>("scooterRange"));
                UserHistPrice1.setCellValueFactory(new PropertyValueFactory<>("scooterPrice"));
                UserHistBattery1.setCellValueFactory(new PropertyValueFactory<>("scooterBattery"));
                UserHistDate1.setCellValueFactory(new PropertyValueFactory<>("rentalTime"));
                returnDateColumn1.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
                costColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void onBackButtonClicked(){

        try{

            Parent root = FXMLLoader.load(getClass().getResource("UserRentDisplay.fxml"));
            Stage appStage = (Stage) UserHistAnorchPane.getScene().getWindow();
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onLogOutButtonClicked(){

        try{

            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            UserHistAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}
