package sample;
import java.sql.Timestamp;

public class Rhistory {

    private int RhistoryID;
    private String scooterModel;
    private int scooterMaxVelocity;
    private String scooterColor;
    private int scooterBasket;
    private int scooterRange;
    private int scooterPrice;
    private int scooterBattery;
    private int rentID;
    private int userID;
    private int rentScooterID;
    private Timestamp rentalTime;
    private Timestamp returnTime;

    public Rhistory(String scooterModel, int scooterMaxVelocity, String scooterColor, int scooterBasket, int scooterRange, int scooterPrice, int scooterBattery, int userID, Timestamp rentalTime, Timestamp returnTime) {
        //int RhistoryID,
        //this.RhistoryID = RhistoryID;
        this.scooterModel = scooterModel;
        this.scooterMaxVelocity = scooterMaxVelocity;
        this.scooterColor = scooterColor;
        this.scooterBasket = scooterBasket;
        this.scooterRange = scooterRange;
        this.scooterPrice = scooterPrice;
        this.scooterBattery = scooterBattery;
        this.rentalTime = rentalTime;
        this.returnTime = returnTime;
       // this.rentID = rentID;
        this.userID = userID;
       // this.rentScooterID = rentScooterID;

    }
}