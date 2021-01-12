package sample;

public class Scooter {

    private int scooterID;
    private String scooterModel;
    private int scooterMaxVelocity;
    private String scooterColor;
    private int scooterAvailability;
    private int scooterBasket;
    private int scooterRange;
    private double scooterPrice;
    private int scooterBattery;

    public Scooter(int scooterID, String scooterModel, int scooterMaxVelocity, String scooterColor, int scooterAvailability, int scooterBasket, int scooterRange, double scooterPrice, int scooterBattery) {
        this.scooterID = scooterID;
        this.scooterModel = scooterModel;
        this.scooterMaxVelocity = scooterMaxVelocity;
        this.scooterColor = scooterColor;
        this.scooterAvailability = scooterAvailability;
        this.scooterBasket = scooterBasket;
        this.scooterRange = scooterRange;
        this.scooterPrice = scooterPrice;
        this.scooterBattery = scooterBattery;
    }

//    public Scooter(String model, int velocity, String color, int i, int basket, int range, int price) {
//
//        this.scooterModel = model;
//        this.scooterMaxVelocity = velocity;
//        this.scooterColor = color;
//        this.scooterAvailability = i;
//        this.scooterBasket = basket;
//        this.scooterRange = range;
//        this.scooterPrice = price;
//
//    }
public Scooter() {

}

    public int getScooterBattery() {
        return scooterBattery;
    }

    public Scooter(String scooterModel, int scooterMaxVelocity, String scooterColor, int scooterAvailability, int scooterBasket, int scooterRange, int scooterPrice, int battery) {

        this.scooterModel = scooterModel;
        this.scooterMaxVelocity = scooterMaxVelocity;
        this.scooterColor = scooterColor;
        this.scooterAvailability = scooterAvailability;
        this.scooterBasket = scooterBasket;
        this.scooterRange = scooterRange;
        this.scooterPrice = scooterPrice;
        this.scooterBattery = battery;
    }

    public int getScooterID() {
        return scooterID;
    }

    public void setScooterID(int scooterID) {
        this.scooterID = scooterID;
    }

    public String getScooterModel() {
        return scooterModel;
    }

    public void setScooterModel(String scooterModel) {
        this.scooterModel = scooterModel;
    }

    public int getScooterMaxVelocity() {
        return scooterMaxVelocity;
    }

    public void setScooterMaxVelocity(int scooterMaxVelocity) {
        this.scooterMaxVelocity = scooterMaxVelocity;
    }

    public String getScooterColor() {
        return scooterColor;
    }

    public void setScooterColor(String scooterColor) {
        this.scooterColor = scooterColor;
    }

    public int getScooterAvailability() {
        return scooterAvailability;
    }

    public void setScooterAvailability(int scooterAvailability) {
        this.scooterAvailability = scooterAvailability;
    }

    public int getScooterBasket() {
        return scooterBasket;
    }

    public void setScooterBasket(int scooterBasket) {
        this.scooterBasket = scooterBasket;
    }

    public int getScooterRange() {
        return scooterRange;
    }

    public void setScooterRange(int scooterRange) {
        this.scooterRange = scooterRange;
    }

    public double getScooterPrice() {
        return scooterPrice;
    }

    public void setScooterPrice(double scooterPrice) {
        this.scooterPrice = scooterPrice;
    }

    public void setScooterBattery(int battery) {
        this.scooterBattery  = battery;
    }
}
