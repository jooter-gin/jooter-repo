package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class UserScooterDisplayController {

    private ObservableList<Scooter> data;
    Parent root;
    Stage stage = new Stage();
    private static int scooterIndex;
    private int scooterAvailability;

    public static int getScooterIndex() {
        return scooterIndex;
    }

    @FXML
    Button profileButton = new Button();
    @FXML
    AnchorPane UsersScooterAnorchPane = new AnchorPane();
    @FXML
    TableView<Scooter> UserScootersTable = new TableView<>();
    @FXML
    TableColumn<Scooter, String> UserScooterModel = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Integer> UserScooterMaxVelocity = new TableColumn<>();
    @FXML
    TableColumn<Scooter, String> UserScooterColor = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Integer> UserScooterAvailability = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Integer> UserScooterBasket = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Integer> UserScooterRange = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Double> UserScooterPrice = new TableColumn<>();
    @FXML
    TableColumn<Scooter, Integer> UserScooterBattery = new TableColumn<>();
    @FXML
    Label UserLabel = new Label();
    @FXML
    ImageView UserScooterImage = new ImageView();
    @FXML
    Button logoutButton = new Button();
    @FXML
    Button rentButton = new Button();
    @FXML
    Button Userrentsbutton = new Button();


    public void onMyScooterButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("UserRentDisplay.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            UsersScooterAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }


    public void onLogOutButtonClicked() {

        try {

            root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            UsersScooterAnorchPane.getScene().getWindow().hide();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    public void initialize() {

        data = FXCollections.observableArrayList();

        try {
            ResultSet rs = DataSource.getInstance().queryScooters();
            while (rs.next()) {

                data.add(new Scooter(rs.getInt(DataSource.getColumnScooterID()),rs.getString(DataSource.getColumnScooterModel()), rs.getInt(DataSource.getColumnScooterMaxVelocity()), rs.getString(DataSource.getColumnScooterColor()), rs.getInt(DataSource.getColumnScooterAvailability()), rs.getInt(DataSource.getColumnScooterBasket()), rs.getInt(DataSource.getColumnScooterRange()), rs.getDouble(DataSource.getColumnScooterPrice()), rs.getInt(DataSource.getColumnScooterBattery())));

            }

        } catch (SQLException e) {
            System.out.println("Cant query scooters");
            e.printStackTrace();
        }

        UserScootersTable.setItems(data);
        UserScooterModel.setCellValueFactory(new PropertyValueFactory<>("scooterModel"));
        UserScooterMaxVelocity.setCellValueFactory(new PropertyValueFactory<>("scooterMaxVelocity"));
        UserScooterColor.setCellValueFactory(new PropertyValueFactory<>("scooterColor"));
        UserScooterAvailability.setCellValueFactory(new PropertyValueFactory<>("scooterAvailability"));
        UserScooterBasket.setCellValueFactory(new PropertyValueFactory<>("scooterBasket"));
        UserScooterRange.setCellValueFactory(new PropertyValueFactory<>("scooterRange"));
        UserScooterPrice.setCellValueFactory(new PropertyValueFactory<>("scooterPrice"));
        UserScooterBattery.setCellValueFactory(new PropertyValueFactory<>("scooterBattery"));

    }


    public void onProfileButtonClicked() {

        try {

            root = FXMLLoader.load(getClass().getResource("jooterProfileInfo.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            UsersScooterAnorchPane.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onRentButtonClicked() {

        if (UserScootersTable.getSelectionModel().getSelectedItem() != null) {
            Scooter scooter = UserScootersTable.getSelectionModel().getSelectedItem();

                scooterIndex = scooter.getScooterID();
                int scooterAvailability = scooter.getScooterAvailability();
                if(scooterAvailability==1) {
                    System.out.println(scooter.getScooterID());
                    scooter.setScooterAvailability(0);
                    ScooterManagement.updateScooterAvailability(scooter);
                    Rent rent = new Rent();
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    rent.setRentalTime(timestamp);
                    rent.setUserID(LoginController.getUserID());
                    rent.setRentScooterID(scooterIndex);
                    Rent.insertIntoRentals(rent);


                    data.clear();


                    try {
                        ResultSet rs = DataSource.getInstance().queryScooters();
                        while (rs.next()) {

                            data.add(new Scooter(rs.getInt(DataSource.getColumnScooterID()), rs.getString(DataSource.getColumnScooterModel()), rs.getInt(DataSource.getColumnScooterMaxVelocity()), rs.getString(DataSource.getColumnScooterColor()), rs.getInt(DataSource.getColumnScooterAvailability()), rs.getInt(DataSource.getColumnScooterBasket()), rs.getInt(DataSource.getColumnScooterRange()), rs.getDouble(DataSource.getColumnScooterPrice()), rs.getInt(DataSource.getColumnScooterBattery())));

                        }

                    } catch (SQLException e) {
                        System.out.println("Cant query scooters");
                        e.printStackTrace();
                    }
                }else{


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Scooter not available");
                    alert.showAndWait();

                }
        }
    }

}




