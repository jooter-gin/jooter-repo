package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class AdminChangeScooterController {

    Parent root;
    Stage stage = new Stage();

    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();
    @FXML
    AnchorPane AdminChangeAnorchPane = new AnchorPane();
    @FXML
    TextField ProfRangeField = new TextField();
    @FXML
    TextField ProfVelocityField = new TextField();
    @FXML
    TextField ProfColorField = new TextField();
    @FXML
    TextField ProfAvailabilityField = new TextField();
    @FXML
    TextField ProfPriceField = new TextField();
    @FXML
    TextField ProfBasketField = new TextField();
    @FXML
    Button ProfButtonSave = new Button();
    @FXML
    Label infAvailabilityLabel = new Label();
    @FXML
    Label infBasketLabel = new Label();
    @FXML
    Label infPriceLabel = new Label();
    @FXML
    Label infColorLabel = new Label();
    @FXML
    Label infRangeLabel = new Label();
    @FXML
    Label infVelocityLabel = new Label();


    public void onBackButtonClicked(){

        try{

            root = FXMLLoader.load(getClass().getResource("JooterAdminPanel.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            AdminChangeAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public void onTextFieldsClicked(){

        infBasketLabel.setVisible(false);
        infAvailabilityLabel.setVisible(false);
        infColorLabel.setVisible(false);
        infPriceLabel.setVisible(false);
        infRangeLabel.setVisible(false);
        infVelocityLabel.setVisible(false);

    }

    public void onLogOutButtonClicked(){

        try {

            root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            AdminChangeAnorchPane.getScene().getWindow().hide();

        }catch(IOException e){
            System.out.println("Error");
        }

    }

    public void onSaveButtonClicked(){

        Scooter scooter = new Scooter();
        int scooterID = AdminProfileController.getScooterIndex();
        ResultSet rs;

        String range = ProfRangeField.getText();
        String velocity = ProfVelocityField.getText();
        String color = ProfColorField.getText();
        String availability = ProfAvailabilityField.getText();
        String basket = ProfBasketField.getText();
        String price = ProfPriceField.getText();



        if(!(range.isEmpty() || range.trim().isEmpty())){
            if(Validate.isNumeric(range)) {

                scooter.setScooterRange(parseInt(range));
                infRangeLabel.setText("Range has been changed");
                infRangeLabel.setTextFill(Color.GREEN);
                infRangeLabel.setVisible(true);
            }
            else{
                try {

                    rs = DataSource.getInstance().queryScooter(scooterID);
                    rs.next();
                    scooter.setScooterRange(parseInt(rs.getString(DataSource.getColumnScooterRange())));

                }catch(SQLException e){
                    e.printStackTrace();
                }
                infRangeLabel.setText("Invalid range. Type a number");
                infRangeLabel.setTextFill(Color.RED);
                infRangeLabel.setVisible(true);
            }


        }else{

            try {

                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterRange(parseInt(rs.getString(DataSource.getColumnScooterRange())));

            }catch(SQLException e){
                e.printStackTrace();
            }


        }

        if(!(velocity.isEmpty() || velocity.trim().isEmpty())){
            if(Validate.isNumeric(velocity)) {

                scooter.setScooterMaxVelocity(parseInt(velocity));
                infVelocityLabel.setText("Velocity has been changed");
                infVelocityLabel.setTextFill(Color.GREEN);
                infVelocityLabel.setVisible(true);
            }
            else{
                try {

                    rs = DataSource.getInstance().queryScooter(scooterID);
                    rs.next();
                    scooter.setScooterMaxVelocity(parseInt(rs.getString(DataSource.getColumnScooterMaxVelocity())));

                }catch(SQLException e){

                    e.printStackTrace();
                }
                infVelocityLabel.setText("Invalid velocity. Type a number");
                infVelocityLabel.setTextFill(Color.RED);
                infVelocityLabel.setVisible(true);
            }

        }else{

            try {

                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterMaxVelocity(parseInt(rs.getString(DataSource.getColumnScooterMaxVelocity())));

            }catch(SQLException e){

                e.printStackTrace();
            }

        }

        if(!(color.isEmpty() || color.trim().isEmpty())){

            scooter.setScooterColor(color);
            infColorLabel.setText("Color has been changed");
            infColorLabel.setTextFill(Color.GREEN);
            infColorLabel.setVisible(true);

        }else{

            try {

                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterColor(rs.getString(DataSource.getColumnScooterColor()));

            }catch(SQLException e){

                e.printStackTrace();
            }

        }

        if(!(availability.isEmpty() || availability.trim().isEmpty())){

            if(availability.equals("1") || availability.equals("0")) {

                scooter.setScooterAvailability(parseInt(availability));
                infAvailabilityLabel.setText("Availability has been changed");
                infAvailabilityLabel.setTextFill(Color.GREEN);
                infAvailabilityLabel.setVisible(true);
            }
            else {
                try {
                    rs = DataSource.getInstance().queryScooter(scooterID);
                    rs.next();
                    scooter.setScooterAvailability(parseInt(rs.getString(DataSource.getColumnScooterAvailability())));

                }catch(SQLException e){

                    e.printStackTrace();
                }
                infAvailabilityLabel.setText("Invalid availability. 0 or 1");
                infAvailabilityLabel.setTextFill(Color.RED);
                infAvailabilityLabel.setVisible(true);
            }


        }else{

            try {
                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterAvailability(parseInt(rs.getString(DataSource.getColumnScooterAvailability())));

            }catch(SQLException e){

                e.printStackTrace();
            }

        }

        if(!(basket.isEmpty() || basket.trim().isEmpty())){
            if(basket.equals("1") || basket.equals("0")) {

                scooter.setScooterBasket(parseInt(basket));
                infBasketLabel.setText("Basket has been changed");
                infBasketLabel.setTextFill(Color.GREEN);
                infBasketLabel.setVisible(true);
            }
            else{
                try {
                    rs = DataSource.getInstance().queryScooter(scooterID);
                    rs.next();
                    scooter.setScooterBasket(parseInt(rs.getString(DataSource.getColumnScooterBasket())));

                }catch(SQLException e){

                    e.printStackTrace();
                }
                infBasketLabel.setText("Invalid basket. 0 or 1");
                infBasketLabel.setTextFill(Color.RED);
                infBasketLabel.setVisible(true);
            }

        }else{

            try {
                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterBasket(parseInt(rs.getString(DataSource.getColumnScooterBasket())));

            }catch(SQLException e){

                e.printStackTrace();
            }

        }

        if(!(price.isEmpty() || price.trim().isEmpty())){
            if(Validate.isNumeric(price)) {

                scooter.setScooterPrice(Double.parseDouble(price));
                infPriceLabel.setText("Price has been changed");
                infPriceLabel.setTextFill(Color.GREEN);
                infPriceLabel.setVisible(true);
            }
            else{
                try {
                    rs = DataSource.getInstance().queryScooter(scooterID);
                    rs.next();
                    scooter.setScooterPrice(Double.parseDouble(rs.getString(DataSource.getColumnScooterPrice())));

                }catch(SQLException e){

                    e.printStackTrace();
                }
                infPriceLabel.setText("Invalid price. Type a number");
                infPriceLabel.setTextFill(Color.RED);
                infPriceLabel.setVisible(true);
            }


        }else{

            try {
                rs = DataSource.getInstance().queryScooter(scooterID);
                rs.next();
                scooter.setScooterPrice(Double.parseDouble(rs.getString(DataSource.getColumnScooterPrice())));

            }catch(SQLException e){

                e.printStackTrace();
            }
        }

        scooter.setScooterID(scooterID);
        DataSource.getInstance().updateScooterss(scooter);
        ProfBasketField.clear();
        ProfAvailabilityField.clear();
        ProfColorField.clear();
        ProfRangeField.clear();
        ProfVelocityField.clear();
        ProfPriceField.clear();
        //System.out.println("ID IS " + scooterID + " " + user.getUserName() + " " + user.getUserSurname() +" " + user.getUserLogin()+ " "+user.getUserPassword()+" "+user.getUserCardNo());

    }

}