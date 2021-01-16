package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class UserAddFundsController {

    Parent root;
    Stage stage = new Stage();

    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();
    @FXML
    AnchorPane ProfProfileAnchorPane = new AnchorPane();
    @FXML
    TextField AmountField = new TextField();
    @FXML
    Button AddButton = new Button();
    @FXML
    Label infAmountLabel = new Label();


    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("jooterProfileInfo.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            ProfProfileAnchorPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onTextFieldsClicked(){
        infAmountLabel.setVisible(false);
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


    public void onAddButtonClicked(){
        int userID = LoginController.getUserID();
        ResultSet rs;
        String amount = AmountField.getText();
        User user = new User();

        if(!(amount.isEmpty() || amount.trim().isEmpty())){
            if(Validate.isNumeric(amount) && parseDouble(amount)<1000) {

                user.setUserAccountFunds(parseDouble(amount));
                infAmountLabel.setText("Funds has been added");
                infAmountLabel.setTextFill(Color.GREEN);
                infAmountLabel.setVisible(true);
                user.setUserId(userID);
                DataSource.getInstance().UpdateUserAccFunds(parseDouble(amount),userID);
                AmountField.clear();
            }
            else{
                try {

                    rs = DataSource.getInstance().queryUser(userID);
                    rs.next();
                    user.setUserAccountFunds(parseDouble(rs.getString(DataSource.getColumnUserAccFunds())));

                }catch(SQLException e){
                    e.printStackTrace();
                }
                infAmountLabel.setText("Type a number from 0 to 999");
                infAmountLabel.setTextFill(Color.RED);
                infAmountLabel.setVisible(true);
            }


        }else{

            try {

                rs = DataSource.getInstance().queryUser(userID);
                rs.next();
                user.setUserAccountFunds(parseDouble(rs.getString(DataSource.getColumnUserAccFunds())));

            }catch(SQLException e){
                e.printStackTrace();
            }


        }

    }

}
