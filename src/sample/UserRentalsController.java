package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class UserRentalsController {

    Stage stage = new Stage();
    Parent root;
    private static ObservableList<ScooterJoin> scooterJoinData;
    private static ObservableList<ScooterJoin> scooterHistoryData;

    @FXML
    Button UserRentsBackButton = new Button();
    @FXML
    Button UserRentsLogoutButton = new Button();
    @FXML
    AnchorPane UserRentsAnorchPane = new AnchorPane();
    @FXML
    TableView<ScooterJoin> UserRentsTable = new TableView<>();
    @FXML
    TableColumn<ScooterJoin,String> UserRentsModel = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsMaxVelocity = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,String> UserRentsColor = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsBasket = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsRange = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Double> UserRentsPrice = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsBattery = new TableColumn<>();
    @FXML
    TableColumn <ScooterJoin,Timestamp> UserRentsDate = new TableColumn<>();
    @FXML
    Button UserRentsReturnButton = new Button();
    @FXML
    Button historyButton = new Button();



    public void initialize(){

        scooterJoinData = FXCollections.observableArrayList();

        try {
            ResultSet joinSet = DataSource.getInstance().joinScooterOnRentals(LoginController.getUserID());
            while(joinSet.next()){

                ScooterJoin sj = new ScooterJoin();
                sj.setScooterModel(joinSet.getString(DataSource.getColumnScooterModel()));
                sj.setScooterMaxVelocity(joinSet.getInt(DataSource.getColumnScooterMaxVelocity()));
                sj.setScooterColor(joinSet.getString(DataSource.getColumnScooterColor()));
                sj.setScooterBasket(joinSet.getInt(DataSource.getColumnScooterBasket()));
                sj.setScooterRange(joinSet.getInt(DataSource.getColumnScooterRange()));
                sj.setScooterPrice(joinSet.getDouble(DataSource.getColumnScooterPrice()));
                sj.setScooterBattery(joinSet.getInt(DataSource.getColumnScooterBattery()));
                sj.setRentalTime(joinSet.getTimestamp(DataSource.getColumnRentsTimestamp()));
                sj.setRentsScooterID(joinSet.getInt(DataSource.getColumnRentsIdscooter()));
                sj.setReturnDate(joinSet.getTimestamp(DataSource.getColumnRentsReturnDate()));
                sj.setScooterAvailability(joinSet.getInt(DataSource.getColumnScooterAvailability()));
                sj.setRentsID(joinSet.getInt(DataSource.getColumnRentsId()));
                sj.setBalance(joinSet.getDouble(DataSource.getColumnRentsBalance()));
                scooterJoinData.add(sj);

            }

            UserRentsTable.setItems(scooterJoinData);
            UserRentsModel.setCellValueFactory(new PropertyValueFactory<>("scooterModel"));
            UserRentsMaxVelocity.setCellValueFactory(new PropertyValueFactory<>("scooterMaxVelocity"));
            UserRentsColor.setCellValueFactory(new PropertyValueFactory<>("scooterColor"));
            UserRentsBasket.setCellValueFactory(new PropertyValueFactory<>("scooterBasket"));
            UserRentsRange.setCellValueFactory(new PropertyValueFactory<>("scooterRange"));
            UserRentsPrice.setCellValueFactory(new PropertyValueFactory<>("scooterPrice"));
            UserRentsBattery.setCellValueFactory(new PropertyValueFactory<>("scooterBattery"));
            UserRentsDate.setCellValueFactory(new PropertyValueFactory<>("rentalTime"));



        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void onHistoryButtonClicked(){

        try {
            Stage appStage = (Stage)UserRentsAnorchPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("UserHistoryScene.fxml"));
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();

        }catch (IOException e){

            e.printStackTrace();
        }


    }

    public void onReturnButtonClicked(){

        if(UserRentsTable.getSelectionModel().getSelectedItem() != null) {

            ScooterJoin sj = UserRentsTable.getSelectionModel().getSelectedItem();
            int scooterId = sj.getRentsScooterID();
            int rentsID = sj.getRentsID();
            Timestamp returnDate = sj.getReturnDate();
            Timestamp rentalDate = sj.getRentalTime();
            long diffTime;
            double price = sj.getScooterPrice();
            Scooter scooter = new Scooter();
            scooter.setScooterID(scooterId);
            scooter.setScooterAvailability(1);
            diffTime = System.currentTimeMillis() - rentalDate.getTime();
            int battery=sj.getScooterBattery();
            while (diffTime>0){
                battery-=10;
                scooter.setScooterBattery(battery);
                diffTime-=3600000;
            }
            scooter.setScooterBattery(scooter.getScooterBattery());
            DataSource.getInstance().updateScooterAvailability(scooter);
            DataSource.getInstance().updateScooterBattery(scooter);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            sj.setReturnDate(timestamp);
            sj.setUserID(LoginController.getUserID());
            DataSource.getInstance().updateReturnDate(timestamp, rentsID);
            long milliseconds = rentalDate.getTime() - timestamp.getTime();
            int seconds = (int) milliseconds / 1000;
            int hours = -(seconds / 3600);
            int minutes = -((seconds % 3600) / 60);
            seconds = -((seconds % 3600) % 60);
            double cost = price * minutes;
            double roundOff = Math.round(cost * 100.0) / 100.0;
            sj.setBalance(cost);
            DataSource.getInstance().updateRentsBalance(roundOff,rentsID);
            User.subtractFromBalance(roundOff,LoginController.getUserID());
            DataSource.getInstance().insertIntoRhistory(sj);
            scooterJoinData.clear();

            try {

                ResultSet joinSet = DataSource.getInstance().joinScooterOnRentals(LoginController.getUserID());
                while (joinSet.next()) {

                    ScooterJoin sjj = new ScooterJoin();
                    sjj.setScooterModel(joinSet.getString(DataSource.getColumnScooterModel()));
                    sjj.setScooterMaxVelocity(joinSet.getInt(DataSource.getColumnScooterMaxVelocity()));
                    sjj.setScooterColor(joinSet.getString(DataSource.getColumnScooterColor()));
                    sjj.setScooterBasket(joinSet.getInt(DataSource.getColumnScooterBasket()));
                    sjj.setScooterRange(joinSet.getInt(DataSource.getColumnScooterRange()));
                    sjj.setScooterPrice(joinSet.getDouble(DataSource.getColumnScooterPrice()));
                    sjj.setScooterBattery(joinSet.getInt(DataSource.getColumnScooterBattery()));
                    sjj.setRentalTime(joinSet.getTimestamp(DataSource.getColumnRentsTimestamp()));
                    sjj.setRentsScooterID(joinSet.getInt(DataSource.getColumnRentsIdscooter()));
                    sjj.setReturnDate(joinSet.getTimestamp(DataSource.getColumnRentsReturnDate()));
                    sjj.setRentsID(joinSet.getInt(DataSource.getColumnRentsId()));
                    sjj.setBalance(joinSet.getDouble(DataSource.getColumnRentsBalance()));
                    scooterJoinData.add(sjj);

                }


                } catch (SQLException e) {


                    e.printStackTrace();
                }

        }
    }

    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            UserRentsAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onLogOutButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            UserRentsAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

}

