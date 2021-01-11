package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScooterManagement {

    public static boolean addScooter (Scooter scooter) {

       return DataSource.getInstance().insertScooters(scooter);

    }

    public static void deleteScooter (int id) {

        DataSource.getInstance().deleteScooter(id);
    }

    public static void updateScooterAvailability(Scooter scooter){

        DataSource.getInstance().updateScooterAvailability(scooter);

    }


    public static ResultSet queryScooter(int scooterID){

        try {
           return DataSource.getInstance().queryScooter(scooterID);
        }catch(SQLException e){

            e.printStackTrace();
        }
        return null;
    }

}
