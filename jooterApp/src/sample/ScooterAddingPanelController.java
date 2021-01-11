package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ScooterAddingPanelController {

    boolean isDisabled;

    @FXML
    AnchorPane addingPanelAnchorPane = new AnchorPane();
    @FXML
    TextField addPanelModelField = new TextField();
    @FXML
    TextField addPanelVelocityField = new TextField();
    @FXML
    TextField addPanelColorField = new TextField();
    @FXML
    TextField addPanelBasketField = new TextField();
    @FXML
    TextField addPanelPriceField = new TextField();
    @FXML
    TextField addPanelBatteryField = new TextField();
    @FXML
    TextField addPanelRangeField = new TextField();
    @FXML
    Label addPanelModelLabel = new Label();
    @FXML
    Label addPanelVelocityLabel = new Label();
    @FXML
    Label addPanelColorLabel = new Label();
    @FXML
    Label addPanelBasketLabel = new Label();
    @FXML
    Label addPanelPriceLabel = new Label();
    @FXML
    Label addPanelBatteryLabel = new Label();
    @FXML
    Label addPanelRangeLabel = new Label();
    @FXML
    Button addPanelConfirmButton = new Button();
    @FXML
    Button addPanelBackButton = new Button();
    @FXML
    Label addPanelsuccessLabel = new Label();
    @FXML
    ImageView addPanScPriceIV = new ImageView();
    @FXML
    ImageView addPanScVeloIV = new ImageView();
    @FXML
    ImageView addPanScBasketIV = new ImageView();
    @FXML
    ImageView addPanScRangeIV = new ImageView();


    public void initialize(){
        addPanelConfirmButton.setDisable(true);
        addPanelsuccessLabel.setVisible(false);
    }

    public void onConfirmButtonClicked(){

        Scooter scooter = new Scooter();

        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;

        flag1 = Validate.isNumeric(addPanelVelocityField.getText().trim()); //dodaj trim
        flag2 = Validate.isNumeric(addPanelBasketField.getText().trim());
        flag3 = Validate.isNumeric(addPanelPriceField.getText().trim());
        flag4 = Validate.isNumeric(addPanelRangeField.getText().trim());

        if(!flag1){

            addPanScVeloIV.setVisible(true);
            addPanelVelocityField.clear();

        }
        if(!flag2){
            addPanScBasketIV.setVisible(true);
            addPanelBasketField.clear();

        }

        if(!flag3){

            addPanScPriceIV.setVisible(true);
            addPanelPriceField.clear();
        }

        if(!flag4){

            addPanScRangeIV.setVisible(true);
            addPanelRangeField.clear();
        }

       if(flag1 && flag2 && flag3 && flag4) {

           scooter.setScooterColor(addPanelColorField.getText().trim());
           scooter.setScooterModel(addPanelModelField.getText().trim());
           scooter.setScooterMaxVelocity(Integer.parseInt(addPanelVelocityField.getText().trim()));
           scooter.setScooterRange(Integer.parseInt(addPanelRangeField.getText().trim()));
           scooter.setScooterBasket(Integer.parseInt(addPanelBasketField.getText().trim()));
           scooter.setScooterPrice(Double.parseDouble(addPanelPriceField.getText().trim()));
           scooter.setScooterAvailability(1);
           scooter.setScooterBattery(100);


           boolean hasCommit = ScooterManagement.addScooter(scooter);
           if (hasCommit) {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("JooterAdminPanel.fxml"));
               try {
                   loader.load();
                   AdminProfileController controller = loader.getController();
                   controller.getAdminScootersTable().refresh();
                   addPanelsuccessLabel.setVisible(true);
                   addPanelsuccessLabel.setText("Scooter successfully added");
                   addPanelsuccessLabel.setTextFill(Color.GREEN);
                   addPanelBasketField.clear();
                   addPanelPriceField.clear();
                   addPanelVelocityField.clear();
                   addPanelModelField.clear();
                   addPanelColorField.clear();
                   addPanelRangeField.clear();
                   addPanelConfirmButton.setDisable(true);

               } catch (IOException e) {
                   e.printStackTrace();
               }
           } else {

               addPanelsuccessLabel.setText("ERROR");
               addPanelsuccessLabel.setTextFill(Color.RED);
               addPanelBasketField.clear();
               addPanelPriceField.clear();
               addPanelVelocityField.clear();
               addPanelModelField.clear();
               addPanelColorField.clear();
               addPanelRangeField.clear();
               addPanelsuccessLabel.setVisible(true);
               addPanelConfirmButton.setDisable(true);


           }

       }else {

           addPanelsuccessLabel.setText("Invalid scooter data");
           addPanelsuccessLabel.setTextFill(Color.RED);
           addPanelsuccessLabel.setVisible(true);
           addPanelConfirmButton.setDisable(true);
       }
    }

public void onBackButtonClicked(){

    try{
        Parent root = FXMLLoader.load(getClass().getResource("JooterAdminPanel.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        addingPanelAnchorPane.getScene().getWindow().hide();

    }catch(IOException e){
        e.printStackTrace();
    }
}


public void onKeyReleased(){

        String scooterModel = addPanelModelField.getText();
        String scooterMaxVelocity = addPanelVelocityField.getText();
        String scooterColor = addPanelColorField.getText();
        String scooterBasket = addPanelBasketField.getText();
        String scooterRange = addPanelRangeField.getText();
        String scooterPrice = addPanelPriceField.getText();
        isDisabled = ((scooterModel.isEmpty() || scooterModel.trim().isEmpty()) || (scooterMaxVelocity.isEmpty() || scooterMaxVelocity.trim().isEmpty()) || (scooterColor.isEmpty() || scooterColor.trim().isEmpty()) || (scooterBasket.isEmpty() || scooterBasket.trim().isEmpty()) || (scooterRange.isEmpty() || scooterRange.trim().isEmpty()) || (scooterPrice.isEmpty() || scooterPrice.trim().isEmpty()));
        addPanelConfirmButton.setDisable(isDisabled);

}

    public void onTextFieldClicked(){

        addPanScRangeIV.setVisible(false);
        addPanScPriceIV.setVisible(false);
        addPanScBasketIV.setVisible(false);
        addPanScVeloIV.setVisible(false);
        addPanelsuccessLabel.setVisible(false);

    }


}
