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
    TableColumn<ScooterJoin,String> UserRentsBasket = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsRange = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Double> UserRentsPrice = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Integer> UserRentsBattery = new TableColumn<>();
    @FXML
    TableColumn <ScooterJoin,Integer> UserRentsDate = new TableColumn<>();
    @FXML
    TableColumn<ScooterJoin,Timestamp> returnDateColumn = new TableColumn<>();
    @FXML
    Button UserRentsReturnButton = new Button();




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
            returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        }catch(SQLException e){
            e.printStackTrace();
        }



    }

    public void onReturnButtonClicked(){

        if(UserRentsTable.getSelectionModel().getSelectedItem() != null) {

            ScooterJoin sj = UserRentsTable.getSelectionModel().getSelectedItem();
            int scooterId = sj.getRentsScooterID();
            int rentsID = sj.getRentsID();
            Timestamp returnDate = sj.getReturnDate();
            if(returnDate == null) {
                Scooter scooter = new Scooter();
                scooter.setScooterID(scooterId);
                scooter.setScooterAvailability(1);
                DataSource.getInstance().updateScooterAvailability(scooter);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DataSource.getInstance().updateReturnDate(timestamp, rentsID);
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
                        scooterJoinData.add(sjj);

                    }


                } catch (SQLException e) {


                    e.printStackTrace();
                }

            }else{

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Scooter already returned");
                alert.showAndWait();

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
