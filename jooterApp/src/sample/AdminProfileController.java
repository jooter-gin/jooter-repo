package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminProfileController {

    private ObservableList<Scooter> adminData;
    private static int scooterIndex;
    private static int adminScooterIndex;
    public static int getScooterIndex() {
        return scooterIndex;
    }

    @FXML
    AnchorPane AdminOuterAnorchPane = new AnchorPane();
    @FXML
    AnchorPane AdminFunctionalAnchorPane = new AnchorPane();
    @FXML
    Label ProfProfileLabel = new Label();
    @FXML
    TextField ProfNameField = new TextField();
    @FXML
    TextField ProfSurnameField = new TextField();
    @FXML
    TextField ProfLoginField = new TextField();
    @FXML
    TextField ProfEmailField = new TextField();
    @FXML
    PasswordField ProfPasswordField = new PasswordField();
    @FXML
    TextField ProfCardNrField = new TextField();
    @FXML
    Label ProfNameLabel = new Label();
    @FXML
    Label ProfSurnameLabel = new Label();
    @FXML
    Label ProfLoginLabel = new Label();
    @FXML
    Label ProfPasswordLabel = new Label();
    @FXML
    Label ProfEmailLabel = new Label();
    @FXML
    Label ProfCardNrLabel = new Label();
    @FXML
    AnchorPane ProfFunctionalyAnorchPane = new AnchorPane();
    @FXML
    Button AdminButtonAdd = new Button();
    @FXML
    Button AdminButtonDelete = new Button();
    @FXML
    Button adminLogOutButton = new Button();
    @FXML
    Button adminUpdateButton = new Button();

    public TableView<Scooter> getAdminScootersTable() {
        return AdminScootersTable;
    }

    @FXML
    TableView<Scooter> AdminScootersTable = new TableView<>();
    @FXML
    TableColumn<Scooter,String> AdminScooterModel = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterMaxVelocity = new TableColumn<>();
    @FXML
    TableColumn<Scooter,String> AdminScooterColor = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterBasket = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterRange = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Double> AdminScooterPrice = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterBattery = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterAvailability = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> AdminScooterID = new TableColumn<>();


    public void initialize(){


        AdminScootersTable.setEditable(true);
        adminData = FXCollections.observableArrayList();

        try {
            ResultSet rs = DataSource.getInstance().queryScooters();
            while(rs.next()){

                adminData.add(new Scooter(rs.getInt(DataSource.getColumnAdminID()),rs.getString(DataSource.getColumnScooterModel()),rs.getInt(DataSource.getColumnScooterMaxVelocity()),rs.getString(DataSource.getColumnScooterColor()),rs.getInt(DataSource.getColumnScooterAvailability()),rs.getInt(DataSource.getColumnScooterBasket()),rs.getInt(DataSource.getColumnScooterRange()),rs.getDouble(DataSource.getColumnScooterPrice()),rs.getInt(DataSource.getColumnScooterBattery())));

            }

        }catch(SQLException e){
            System.out.println("Cant query scooters");
            e.printStackTrace();
        }

        AdminScootersTable.setItems(adminData);
        AdminScooterID.setCellValueFactory(new PropertyValueFactory<>("scooterID"));
        AdminScooterModel.setCellValueFactory(new PropertyValueFactory<>("scooterModel"));
        AdminScooterMaxVelocity.setCellValueFactory(new PropertyValueFactory<>("scooterMaxVelocity"));
        AdminScooterColor.setCellValueFactory(new PropertyValueFactory<>("scooterColor"));
        AdminScooterAvailability.setCellValueFactory(new PropertyValueFactory<>("scooterAvailability"));
        AdminScooterBasket.setCellValueFactory(new PropertyValueFactory<>("scooterBasket"));
        AdminScooterRange.setCellValueFactory(new PropertyValueFactory<>("scooterRange"));
        AdminScooterPrice.setCellValueFactory(new PropertyValueFactory<>("scooterPrice"));
        AdminScooterBattery.setCellValueFactory(new PropertyValueFactory<>("scooterBattery"));


    }

    public void onAddButtonClicked(){

    try{

        Parent root = FXMLLoader.load(getClass().getResource("scooterAddingPanel.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        AdminOuterAnorchPane.getScene().getWindow().hide();


    }catch(IOException e){
        e.printStackTrace();
    }

    }


    public void onDeleteButtonClicked(){

        if(AdminScootersTable.getSelectionModel().getSelectedItem() != null) {
            Scooter scooter = AdminScootersTable.getSelectionModel().getSelectedItem();
            if (scooter.getScooterAvailability() == 1) {
                scooterIndex = scooter.getScooterID();
                ScooterManagement.deleteScooter(scooterIndex);
                adminData.clear();
                try {
                    ResultSet rs = DataSource.getInstance().queryScooters();
                    while (rs.next()) {

                        adminData.add(new Scooter(rs.getInt(DataSource.getColumnScooterID()), rs.getString(DataSource.getColumnScooterModel()), rs.getInt(DataSource.getColumnScooterMaxVelocity()), rs.getString(DataSource.getColumnScooterColor()), rs.getInt(DataSource.getColumnScooterAvailability()), rs.getInt(DataSource.getColumnScooterBasket()), rs.getInt(DataSource.getColumnScooterRange()), rs.getDouble(DataSource.getColumnScooterPrice()), rs.getInt(DataSource.getColumnScooterBattery())));

                    }

                } catch (SQLException e) {
                    System.out.println("Cant query scooters");
                    e.printStackTrace();
                }

            }else{

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Scooter not available");
                alert.setContentText("Customer needs to return this scooter first!");

                alert.showAndWait();
            }
        }
    }

    public void onLogOutButtonClicked(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            AdminOuterAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void onUpdateButtonClicked(){

        if (AdminScootersTable.getSelectionModel().getSelectedItem() != null) {
            Scooter scooter = AdminScootersTable.getSelectionModel().getSelectedItem();
            scooterIndex = scooter.getScooterID();

        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminChangeScooter.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            AdminOuterAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
