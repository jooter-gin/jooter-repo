package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;

public class userProfileInfoController {

    Stage stage = new Stage();
    Parent root;

    @FXML
    AnchorPane ProfProfileAnchorPane = new AnchorPane();
    @FXML
    Label nameDataLabel = new Label();
    @FXML
    Label surnameDataLabel = new Label();
    @FXML
    Label loginDataLabel = new Label();
    @FXML
    Label emailDataLabel = new Label();
    @FXML
    Label cardNoDataLabel = new Label();
    @FXML
    Button changeButton = new Button();
    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();
    @FXML
    Button deleteAccButton = new Button();
    @FXML
    Label balanceLabel = new Label();
    @FXML
    Label fundsLabel = new Label();
    @FXML
    Label infBalanceLabel = new Label();


    public void initialize(){

        try {
            ResultSet rs = DataSource.getInstance().queryUser(LoginController.getUserID());
            while (rs.next()){

                nameDataLabel.setText(rs.getString(DataSource.getColumnUserName()));
                surnameDataLabel.setText(rs.getString(DataSource.getColumnUserSurname()));
                loginDataLabel.setText(rs.getString(DataSource.getColumnUserLogin()));
                emailDataLabel.setText(rs.getString(DataSource.getColumnUserEmail()));
                cardNoDataLabel.setText(rs.getString(DataSource.getColumnUserCardNo()));
                balanceLabel.setText(rs.getString(DataSource.getColumnUserAccBalance()));
                fundsLabel.setText(rs.getString(DataSource.getColumnUserAccFunds()));
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
    }


    public void onChangeButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("JooterProfile.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            ProfProfileAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("UserScooterDisplay.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            ProfProfileAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onLogOutButtonClicked(){

        try {

            root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            ProfProfileAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){
            System.out.println("Error");
        }

    }

    public void onDeleteAccountButtonClicked() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Your account and data will be lost in the process");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {

                DataSource.getInstance().deleteFromUsers(LoginController.getUserID());
                DataSource.getInstance().deleteFromRhistory(LoginController.getUserID());

                try {
                    root = FXMLLoader.load(getClass().getResource("JooterLogin.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();
                    ProfProfileAnchorPane.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void onAddFundsButtonClicked(){
        try {

            root = FXMLLoader.load(getClass().getResource("UserAddFunds.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            ProfProfileAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){
            System.out.println("Error");
        }
    }
    public void onPayButtonClicked() throws SQLException {
        User user = new User();
        int userID = LoginController.getUserID();
        user.setUserId(userID);
        ResultSet rs;
        rs = DataSource.getInstance().queryUserBalance(userID);
        rs.next();
        ResultSet rs2;
        rs2 = DataSource.getInstance().queryUserFunds(userID);
        rs2.next();
        if(rs2.getDouble(userID)>=abs(rs.getDouble(userID))){
            DataSource.getInstance().UpdateUserAccFunds(rs2.getDouble(userID)+rs.getDouble(userID),userID);
            DataSource.getInstance().updateUserAccBalance(0,userID);
            ResultSet rsnew;
            rsnew = DataSource.getInstance().queryUserBalance(userID);
            rsnew.next();
            ResultSet rs2new;
            rs2new = DataSource.getInstance().queryUserFunds(userID);
            rs2new.next();
            balanceLabel.setText(rsnew.getString(DataSource.getColumnUserAccBalance()));
            fundsLabel.setText(rs2new.getString(DataSource.getColumnUserAccFunds()));
        }
        else{
            infBalanceLabel.setTextFill(Color.RED);
            infBalanceLabel.setText("Niewystarczająca ilość środków!");
            infBalanceLabel.setVisible(true);
        }

    }
}
