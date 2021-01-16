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
    @FXML
    Button filterButton = new Button();
    @FXML
    MenuButton menuButton = new MenuButton();
    @FXML
    TextField filterTextField = new TextField();
    @FXML
    TextField filterTextField2 = new TextField();

    private int option = 5;


    public void onFilterButtonClicked(){

        String input = filterTextField.getText().trim();
        String input2 = filterTextField2.getText().trim();
        ResultSet rs = null;
        boolean isChanged = false;

        if(!input.isEmpty() || option == 0 || option == 5) {

            switch (option) {

                case 0:

                    try {
                        rs = DataSource.getInstance().queryScooters();
                        isChanged = true;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;

                case 1:
                    if (Validate.isNumeric(input)) {

                        if (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 0) {

                            try {
                                rs = DataSource.getInstance().selectAva(Integer.parseInt(input));
                                isChanged = true;

                            } catch (SQLException e) {

                                e.printStackTrace();
                            }
                        } else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid data type. Insert 0 or 1");
                            alert.showAndWait();

                        }

                    } else {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid data type. Insert 0 or 1");
                        alert.showAndWait();
                    }

                    break;

                case 2:

                    if(!(Validate.hasSpace(input))) {

                        try {
                            rs = DataSource.getInstance().selectColor(input);
                            isChanged = true;

                        } catch (SQLException e) {

                            e.printStackTrace();
                        }
                    }else{

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Color name cannot contain whitespaces");
                        alert.showAndWait();
                    }
                    break;

                case 3:

                    if (Validate.isNumeric(input)) {

                        try {
                            rs = DataSource.getInstance().selectPrice(Double.parseDouble(input));
                            isChanged = true;

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid data type. Insert a number");
                        alert.showAndWait();
                    }

                    break;

                case 4:

                    if(!input2.isEmpty()) {

                        if (Validate.isNumeric(input) && Validate.isNumeric(input2)) {

                            try {
                                rs = DataSource.getInstance().selectRange(Integer.parseInt(input),Integer.parseInt(input2));
                                isChanged = true;

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else {

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid data type. Insert a number");
                            alert.showAndWait();
                        }

                    }else{

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Please insert both parameters or choose \"Show all\" option");
                        alert.showAndWait();

                    }
                    break;

                case 5:
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please choose an option from Filter options menu");
                    alert.showAndWait();
                    break;
            }

            if (isChanged) {

                try {

                    if (rs.isBeforeFirst()) {

                        data.clear();

                        try {

                            while (rs.next()) {

                                data.add(new Scooter(rs.getInt(DataSource.getColumnScooterID()), rs.getString(DataSource.getColumnScooterModel()), rs.getInt(DataSource.getColumnScooterMaxVelocity()), rs.getString(DataSource.getColumnScooterColor()), rs.getInt(DataSource.getColumnScooterAvailability()), rs.getInt(DataSource.getColumnScooterBasket()), rs.getInt(DataSource.getColumnScooterRange()), rs.getDouble(DataSource.getColumnScooterPrice()), rs.getInt(DataSource.getColumnScooterBattery())));
                            }
                            rs.close();
                            UserScootersTable.setItems(data);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("No matches found");
                        alert.showAndWait();

                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            filterTextField.clear();
            filterTextField2.clear();

        }else{

            filterTextField2.clear();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            if(option == 4 ) {
                alert.setContentText("Please insert both parameters or choose \"Show all\" option");
            }else{
                alert.setContentText("Please insert a parameter or choose \"Show all\" option");
            }
            alert.showAndWait();

        }


    }

    public void onMenuButtonClicked0(){

        this.option = 0;
        filterTextField2.setVisible(false);
        filterTextField.setVisible(false);
    }

    public void onMenuButtonClicked1(){

        this.option = 1;
        filterTextField2.setVisible(false);
        filterTextField.setVisible(true);
    }

    public void onMenuButtonClicked2(){

        this.option = 2;
        filterTextField2.setVisible(false);
        filterTextField.setVisible(true);
    }

    public void onMenuButtonClicked3(){

        this.option = 3;
        filterTextField2.setVisible(false);
        filterTextField.setVisible(true);
    }

    public void onMenuButtonClicked4(){

        this.option = 4;
        filterTextField2.setVisible(true);
        filterTextField.setVisible(true);
    }

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




