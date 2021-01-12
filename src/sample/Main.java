package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    DataSource ds = DataSource.getInstance();


    @Override
    public void stop() throws Exception {
        ds.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ds.open();
        Parent root = FXMLLoader.load(getClass().getResource("jooterLogin.fxml"));
        primaryStage.setTitle("Jooter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
