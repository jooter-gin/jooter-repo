package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Rent {

    private int rentID;
    private int userID;
    private int rentScooterID;
    private Timestamp rentalTime;
    private Timestamp returnDate;

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public Rent(){


}

    public int getRentID() {
        return rentID;
    }



    public int getUserID() {
        return userID;
    }

    public int getRentScooterID() {
        return rentScooterID;
    }



    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Timestamp rentalTime) {
        this.rentalTime = rentalTime;
    }

    public Rent(int rentID, int userID, int rentScooterID, Timestamp rentalTime) {
        this.rentID = rentID;
        this.userID = userID;
        this.rentScooterID = rentScooterID;
        this.rentalTime = rentalTime;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setRentScooterID(int rentScooterID) {
        this.rentScooterID = rentScooterID;
    }


    public static void insertIntoRentals(Rent rent){

        DataSource.getInstance().insertIntoRentals(rent);
    }

    public static ResultSet joinScooterOnRentals(){

    try {
      return   DataSource.getInstance().joinScooterOnRentals(LoginController.getUserID());
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
    }
}
