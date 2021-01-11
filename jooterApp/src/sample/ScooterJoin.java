package sample;

import java.sql.Timestamp;

public class ScooterJoin {


    private String scooterModel;
    private int scooterMaxVelocity;
    private String scooterColor;
    private int scooterBasket;
    private int scooterRange;
    private double scooterPrice;
    private int scooterBattery;
    private Timestamp rentalTime;
    private int rentsScooterID;
    private Timestamp returnDate;
    private int scooterAvailability;
    private int rentsID;


    public int getRentsID() {
        return rentsID;
    }

    public void setRentsID(int rentsID) {
        this.rentsID = rentsID;
    }

    public ScooterJoin(){


    }

    public int getScooterAvailability() {
        return scooterAvailability;
    }

    public void setScooterAvailability(int scooterAvailability) {
        this.scooterAvailability = scooterAvailability;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public int getRentsScooterID() {
        return rentsScooterID;
    }

    public void setRentsScooterID(int rentsScooterID) {
        this.rentsScooterID = rentsScooterID;
    }

    public String getScooterModel() {
        return scooterModel;
    }

    public int getScooterMaxVelocity() {
        return scooterMaxVelocity;
    }

    public String getScooterColor() {
        return scooterColor;
    }



    public int getScooterBasket() {
        return scooterBasket;
    }

    public int getScooterRange() {
        return scooterRange;
    }

    public double getScooterPrice() {
        return scooterPrice;
    }

    public int getScooterBattery() {
        return scooterBattery;
    }

    public Timestamp getRentalTime() {
        return rentalTime;
    }

    public void setScooterModel(String scooterModel) {
        this.scooterModel = scooterModel;
    }

    public void setScooterMaxVelocity(int scooterMaxVelocity) {
        this.scooterMaxVelocity = scooterMaxVelocity;
    }

    public void setScooterColor(String scooterColor) {
        this.scooterColor = scooterColor;
    }



    public void setScooterBasket(int scooterBasket) {
        this.scooterBasket = scooterBasket;
    }

    public void setScooterRange(int scooterRange) {
        this.scooterRange = scooterRange;
    }

    public void setScooterPrice(double scooterPrice) {
        this.scooterPrice = scooterPrice;
    }

    public void setScooterBattery(int scooterBattery) {
        this.scooterBattery = scooterBattery;
    }

    public void setRentalTime(Timestamp rentalTime) {
        this.rentalTime = rentalTime;
    }





}
