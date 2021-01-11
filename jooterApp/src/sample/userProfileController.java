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

public class userProfileController {

    Parent root;
    Stage stage = new Stage();

    @FXML
    Button logoutButton = new Button();
    @FXML
    Button backButton = new Button();
    @FXML
    AnchorPane ProfProfileAnchorPane = new AnchorPane();
    @FXML
    TextField ProfNameField = new TextField();
    @FXML
    TextField ProfSurnameField = new TextField();
    @FXML
    TextField ProfLoginField = new TextField();
    @FXML
    TextField ProfEmailField = new TextField();
    @FXML
    TextField ProfCardNrField = new TextField();
    @FXML
    PasswordField ProfPasswordField = new PasswordField();
    @FXML
    Button ProfButtonSave = new Button();
    @FXML
    Label infEmailLabel = new Label();
    @FXML
    Label infPassLabel = new Label();
    @FXML
    Label infCardNoLabel = new Label();
    @FXML
    Label infLoginLabel = new Label();
    @FXML
    Label infNameLabel = new Label();
    @FXML
    Label infSurnameLabel = new Label();




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

    infPassLabel.setVisible(false);
    infEmailLabel.setVisible(false);
    infLoginLabel.setVisible(false);
    infCardNoLabel.setVisible(false);
    infNameLabel.setVisible(false);
    infSurnameLabel.setVisible(false);

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


    public void onSaveButtonClicked(){


        int userID = LoginController.getUserID();
        ResultSet rs;


       String name = ProfNameField.getText();
       String surname = ProfSurnameField.getText();
       String login = ProfLoginField.getText();
       String email = ProfEmailField.getText();
       String password = ProfPasswordField.getText();
       String cardNo = ProfCardNrField.getText();

       User user = new User();

       if(!(name.isEmpty() || name.trim().isEmpty())){

           user.setUserName(name);
           infNameLabel.setText("Name has been changed");
           infNameLabel.setTextFill(Color.GREEN);
           infNameLabel.setVisible(true);


       }else{

           try {

               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserName(rs.getString(DataSource.getColumnUserName()));

           }catch(SQLException e){
               e.printStackTrace();
           }


       }

       if(!(surname.isEmpty() || surname.trim().isEmpty())){

           user.setUserSurname(surname);
           infSurnameLabel.setText("Surname has been changed");
           infSurnameLabel.setTextFill(Color.GREEN);
           infSurnameLabel.setVisible(true);

        }else{

           try {

               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserSurname(rs.getString(DataSource.getColumnUserSurname()));

           }catch(SQLException e){

               e.printStackTrace();
           }

       }

       if(!(login.isEmpty() || login.trim().isEmpty())){

           if(Validate.validateUserLogin(login)) {

               user.setUserLogin(login);
               infLoginLabel.setText("Login has been changed");
               infLoginLabel.setTextFill(Color.GREEN);
               infLoginLabel.setVisible(true);

           }else{

               try {

                   rs = DataSource.getInstance().queryUser(userID);
                   rs.next();
                   user.setUserLogin(rs.getString(DataSource.getColumnUserLogin()));

               }catch(SQLException e){

                   e.printStackTrace();
               }

               infLoginLabel.setTextFill(Color.RED);
               infLoginLabel.setText("Invalid login");
               infLoginLabel.setVisible(true);

           }

       }else{

           try {

               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserLogin(rs.getString(DataSource.getColumnUserLogin()));

           }catch(SQLException e){

               e.printStackTrace();
           }


       }

       if(!(password.isEmpty() || password.trim().isEmpty())){

           if(Validate.validateUserPassword(password)) {
               user.setUserPassword(password);
               infPassLabel.setText("Password has been changed");
               infPassLabel.setTextFill(Color.GREEN);
               infPassLabel.setVisible(true);

           }else{

               try {
                   rs = DataSource.getInstance().queryUser(userID);
                   rs.next();
                   user.setUserPassword(rs.getString(DataSource.getColumnUserPassword()));

               }catch(SQLException e){

                   e.printStackTrace();
               }

               infPassLabel.setText("Invalid password");
               infPassLabel.setTextFill(Color.RED);
               infPassLabel.setVisible(true);

           }

       }else{

           try {
               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserPassword(rs.getString(DataSource.getColumnUserPassword()));

           }catch(SQLException e){

               e.printStackTrace();
           }

       }

       if(!(email.isEmpty() || email.trim().isEmpty())){
           if(Validate.validateEmail(email)) {

               user.setUserEmail(email);
               infEmailLabel.setText("Email has been changed");
               infEmailLabel.setTextFill(Color.GREEN);
               infEmailLabel.setVisible(true);

           }else{

               try {
                   rs = DataSource.getInstance().queryUser(userID);
                   rs.next();
                   user.setUserEmail(rs.getString(DataSource.getColumnUserEmail()));

               }catch(SQLException e){

                   e.printStackTrace();
               }

               infEmailLabel.setText("Invalid email");
               infEmailLabel.setTextFill(Color.RED);
               infEmailLabel.setVisible(true);

           }
       }else{

           try {
               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserEmail(rs.getString(DataSource.getColumnUserEmail()));

           }catch(SQLException e){

               e.printStackTrace();
           }

       }

       if(!(cardNo.isEmpty() || cardNo.trim().isEmpty())){

           if(Validate.validateCardNumber(cardNo)) {

               user.setUserCardNo(cardNo);
               infCardNoLabel.setText("Card number has been changed");
               infCardNoLabel.setTextFill(Color.GREEN);
               infCardNoLabel.setVisible(true);

           }else{

               try {
                   rs = DataSource.getInstance().queryUser(userID);
                   rs.next();
                   user.setUserCardNo(rs.getString(DataSource.getColumnUserCardNo()));

               }catch(SQLException e){

                   e.printStackTrace();
               }

               infCardNoLabel.setText("Invalid card number");
               infCardNoLabel.setTextFill(Color.RED);
               infCardNoLabel.setVisible(true);

           }

       }else{

           try {
               rs = DataSource.getInstance().queryUser(userID);
               rs.next();
               user.setUserCardNo(rs.getString(DataSource.getColumnUserCardNo()));

           }catch(SQLException e){

               e.printStackTrace();
           }
       }


       user.setUserId(userID);
       DataSource.getInstance().updateUser(user);
       ProfPasswordField.clear();
       ProfEmailField.clear();
       ProfLoginField.clear();
       ProfNameField.clear();
       ProfSurnameField.clear();
       ProfCardNrField.clear();
      // System.out.println("ID IS " + userID + " " + user.getUserName() + " " + user.getUserSurname() +" " + user.getUserLogin()+ " "+user.getUserPassword()+" "+user.getUserCardNo());

    }

}
