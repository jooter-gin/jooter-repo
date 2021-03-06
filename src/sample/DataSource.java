package sample;

import javax.xml.transform.Result;
import java.sql.*;

public final class DataSource {

    //private static final String DB_NAME = "zchmtson";
//
   //private static final String CONNECTION_STRING = "jdbc:postgresql://hattie.db.elephantsql.com:5432/" + DB_NAME;

    private static final String DB_NAME = "jooter5";

   private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/" + DB_NAME;

    private Connection c;

    private static final DataSource instance = new DataSource();

    private PreparedStatement insertIntoUsers;

    private PreparedStatement insertIntoScooters;

    private PreparedStatement deleteFromScooters;

    private PreparedStatement queryUsers;

    private PreparedStatement queryAdmins;

    private PreparedStatement queryScooters;

    private PreparedStatement updateUsers;

    private PreparedStatement queryUser;

    private PreparedStatement queryRents;

    private PreparedStatement updateScooters;

    private PreparedStatement updateScooterss;

    private PreparedStatement updateScooterAvailability;

    private PreparedStatement updateScooterBattery;

    private PreparedStatement insertIntoRentals;

    private PreparedStatement joinScootersOnRentals;

    private PreparedStatement joinScootersHistory;

    private PreparedStatement queryScooter;

    private PreparedStatement updateReturnDate;

    private PreparedStatement insertIntoRhistory;

    private PreparedStatement deleteFromUsers;

    private PreparedStatement updateRentsBalance;

    private PreparedStatement updateUserAccBalance;

    private PreparedStatement updateUserAccFunds;

    private PreparedStatement queryUserBalance;

    private PreparedStatement queryUserFunds;

    private PreparedStatement queryRhistory;

    private PreparedStatement deleteFromRhistory;

    private PreparedStatement selectMaxVelocity;

    private PreparedStatement selectColor;

    private PreparedStatement selectRange;

    private PreparedStatement selectAvaliability;

    private PreparedStatement selectPrice;

    public static final int REGULAR_ORDER = 1;

    public static final int ASC_ORDER = 2;

    public static final int DESC_ORDER = 3;

    private DataSource() {
    }

    public static DataSource getInstance() {

        return instance;
    }

    private static final String TABLE_USERS = "Users";
    private static final String COLUMN_USER_ID = "Id";
    private static final String COLUMN_USER_NAME = "Name";
    private static final String COLUMN_USER_SURNAME = "Surname";
    private static final String COLUMN_USER_LOGIN = "Login";
    private static final String COLUMN_USER_PASSWORD = "Password";
    private static final String COLUMN_USER_EMAIL = "Email";
    private static final String COLUMN_USER_CARD_NO = "CardNo";
    private static final String COLUMN_USER_ACC_BALANCE = "AccountBalance";
    private static final String COLUMN_USER_ACC_FUNDS  = "AccountFunds";

    public static String getColumnUserLogin() {
        return COLUMN_USER_LOGIN;
    }

    public static String getColumnUserID() {
        return COLUMN_USER_ID;
    }

    public static String getColumnUserPassword() {
        return COLUMN_USER_PASSWORD;
    }

    public static String getColumnUserEmail() {
        return COLUMN_USER_EMAIL;
    }

    public static String getColumnUserName() {
        return COLUMN_USER_NAME;
    }

    public static String getColumnUserSurname() {
        return COLUMN_USER_SURNAME;
    }

    public static String getColumnUserCardNo() {
        return COLUMN_USER_CARD_NO;
    }

    public static String getColumnUserAccBalance() {
        return COLUMN_USER_ACC_BALANCE;
    }

    public static String getColumnUserAccFunds() {
        return COLUMN_USER_ACC_FUNDS;
    }

    private static final String TABLE_ADMINS = "Admins";
    private static final String COLUMN_ADMIN_ID = "Id";
    private static final String COLUMN_ADMIN_NAME = "Name";
    private static final String COLUMN_ADMIN_SURNAME = "Surname";
    private static final String COLUMN_ADMIN_LOGIN = "Login";
    private static final String COLUMN_ADMIN_PASSWORD = "Password";
    private static final String COLUMN_ADMIN_EMAIL = "Email";

    public static String getColumnAdminID() {
        return COLUMN_ADMIN_ID;
    }

    public static String getColumnAdminLogin() {
        return COLUMN_ADMIN_LOGIN;
    }

    public static String getColumnAdminPassword() {
        return COLUMN_ADMIN_PASSWORD;
    }

    public static String getColumnAdminEmail() {
        return COLUMN_ADMIN_EMAIL;
    }

    private static final String TABLE_SCOOTERS = "Scooters";
    private static final String COLUMN_SCOOTER_ID = "Id";
    private static final String COLUMN_SCOOTER_MODEL = "Model";
    private static final String COLUMN_SCOOTER_MAX_VELOCITY = "MaxVelocity";
    private static final String COLUMN_SCOOTER_COLOR = "ScooterColor";
    private static final String COLUMN_SCOOTER_AVAILABILITY = "ScooterAvailability";
    private static final String COLUMN_SCOOTER_BASKET = "ScooterBasket";
    private static final String COLUMN_SCOOTER_RANGE = "ScooterRange";
    private static final String COLUMN_SCOOTER_PRICE = "ScooterPrice";
    private static final String COLUMN_SCOOTER_BATTERY = "ScooterBattery";

    public static String getColumnScooterID() {return COLUMN_SCOOTER_ID;}

    private static final String TABLE_RENTS = "Rents";
    private static final String COLUMN_RENTS_ID = "Id";
    //private static final String COLUMN_RENTS_RENTALDATE = "RentalDate";
    private static final String COLUMN_RENTS_TIMESTAMP= "RentalTime";
    private static final String COLUMN_RENTS_RETURN_DATE = "ReturnDate";
    private static final String COLUMN_RENTS_IDUSER = "IdUser";
    private static final String COLUMN_RENTS_IDSCOOTER = "IdScooter";
    //private static final String COLUMN_RENTS_IDAMDIN = "IdAdmin";
    private static final String COLUMN_RENTS_BALANCE = "Balance";

    public static String getColumnRentsID() {return COLUMN_RENTS_ID;}
    private static final String TABLE_REPORTS = "Reports";
    private static final String COLUMN_REPORTS_ID = "ID";
    private static final String COLUMN_REPORTS_USER_ID = "UserID";
    private static final String COLUMN_REPORT_SUBMISSION_DATE = "SubmissionDate";
    private static final String COLUMN_REPORTS_TEXT = "ReportText";
    private static final String COLUMN_REPORTS_TITLE = "ReportTitle";

    public static String getColumnReportsDestination() {
        return COLUMN_REPORTS_DESTINATION;
    }

    private static final String COLUMN_REPORTS_DESTINATION = "ReportDestination";

    private final String CREATE_REPORTS_TABLE = "CREATE TABLE IF NOT EXISTS " + " " + TABLE_REPORTS +
            "(" +
            COLUMN_REPORTS_ID + " SERIAL PRIMARY KEY, " +
            COLUMN_REPORTS_USER_ID + " INT NOT NULL, " +
            COLUMN_REPORT_SUBMISSION_DATE + " TIMESTAMP NOT NULL, "+
            COLUMN_REPORTS_TITLE + " TEXT NOT NULL, " +
            COLUMN_REPORTS_TEXT + " TEXT NOT NULL, " +
            COLUMN_REPORTS_DESTINATION + " INT NOT NULL ) ";





    private final String CREATE_RENTS_TABLE = " CREATE TABLE IF NOT EXISTS " + " " + TABLE_RENTS +
            "( " +
            COLUMN_RENTS_ID + " SERIAL PRIMARY KEY, " +
           // COLUMN_RENTS_RENTALDATE + " DATE NOT NULL, " +
            COLUMN_RENTS_TIMESTAMP + " TIMESTAMP NOT NULL , " +
            COLUMN_RENTS_RETURN_DATE + " TIMESTAMP , " +
            COLUMN_RENTS_IDUSER + " INT, "  +
            COLUMN_RENTS_IDSCOOTER + " INT, "  +
            COLUMN_RENTS_BALANCE + " DOUBLE PRECISION, " +
            " FOREIGN KEY ( " + COLUMN_RENTS_IDUSER + " ) REFERENCES " + TABLE_USERS + " ( " + COLUMN_USER_ID + " ) " + " ON DELETE SET NULL, " +
            " FOREIGN KEY ( " + COLUMN_RENTS_IDSCOOTER + " ) REFERENCES " + TABLE_SCOOTERS + " ( " + COLUMN_SCOOTER_ID + " ) " + " ON DELETE SET NULL" + " ) ";
            //" FOREIGN KEY ( " + COLUMN_RENTS_IDAMDIN + " ) REFERENCES " + TABLE_ADMINS + " ( " + COLUMN_ADMIN_ID + " ) )";


    public static String getColumnRentsReturnDate() {
        return COLUMN_RENTS_RETURN_DATE;
    }

    private final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS "+" " + TABLE_USERS +
            "(" +
            COLUMN_USER_ID + " SERIAL PRIMARY KEY,  " +
            COLUMN_USER_NAME + " varchar(20) NOT NULL, " +
            COLUMN_USER_SURNAME + " varchar(20) NOT NULL, " +
            COLUMN_USER_LOGIN + " varchar(20) NOT NULL, "  +
            COLUMN_USER_PASSWORD  + " varchar(20) NOT NULL, "  +
            COLUMN_USER_EMAIL + " varchar(20) NOT NULL, " +
            COLUMN_USER_CARD_NO + " varchar(20) NOT NULL, " +
            COLUMN_USER_ACC_FUNDS + " DOUBLE PRECISION NOT NULL, " +
            COLUMN_USER_ACC_BALANCE + " DOUBLE PRECISION NOT NULL )";

    private final String CREATE_ADMINS_TABLE = "CREATE TABLE IF NOT EXISTS " + " " + TABLE_ADMINS +
            "(" +
            COLUMN_ADMIN_ID+ " SERIAL PRIMARY KEY, " +
            COLUMN_ADMIN_NAME + " varchar(20) NOT NULL, " +
            COLUMN_ADMIN_SURNAME +" varchar(20) NOT NULL, "+
            COLUMN_ADMIN_LOGIN + " varchar(20) NOT NULL,"  +
            COLUMN_ADMIN_PASSWORD  + " varchar(20) NOT NULL, "  +
            COLUMN_ADMIN_EMAIL + " varchar(20) NOT NULL) ";

    private final String CREATE_SCOOTERS_TABLE = "CREATE TABLE IF NOT EXISTS "+" " + TABLE_SCOOTERS+
            "(" +
            COLUMN_SCOOTER_ID + " SERIAL PRIMARY KEY, " +
            COLUMN_SCOOTER_MODEL + " varchar(20) NOT NULL, " +
            COLUMN_SCOOTER_MAX_VELOCITY + " INT NOT NULL, "  +
            COLUMN_SCOOTER_COLOR  + " varchar(20) NOT NULL, "  +
            COLUMN_SCOOTER_AVAILABILITY + " INT NOT NULL, " +
            COLUMN_SCOOTER_BASKET + " INT NOT NULL, " +
            COLUMN_SCOOTER_RANGE + " INT NOT NULL, " +
            COLUMN_SCOOTER_PRICE + " DOUBLE PRECISION NOT NULL, " +
            COLUMN_SCOOTER_BATTERY + " INT NOT NULL )";

    private static final String TABLE_RHISTORY = "Rhistory";

    private final String CREATE_RHISTORY_TABLE = " CREATE TABLE IF NOT EXISTS " + " " + TABLE_RHISTORY +
            "( " +
            COLUMN_SCOOTER_MODEL + " varchar(20) NOT NULL, " +
            COLUMN_SCOOTER_MAX_VELOCITY + " INT NOT NULL, "  +
            COLUMN_SCOOTER_COLOR  + " varchar(20) NOT NULL, "  +
            COLUMN_SCOOTER_BASKET + " INT NOT NULL, " +
            COLUMN_SCOOTER_RANGE + " INT NOT NULL, " +
            COLUMN_SCOOTER_PRICE + " INT NOT NULL, " +
            COLUMN_SCOOTER_BATTERY + " INT NOT NULL , " +
            COLUMN_RENTS_TIMESTAMP + " TIMESTAMP NOT NULL , " +
            COLUMN_RENTS_RETURN_DATE + " TIMESTAMP , " +
            COLUMN_RENTS_IDUSER + " INT, " +
            COLUMN_SCOOTER_ID + " INT, " +
            COLUMN_USER_NAME + " varchar(20), " +
            COLUMN_USER_SURNAME + " varchar(20), " +
            COLUMN_RENTS_BALANCE + " DOUBLE PRECISION " +" ) ";


    public static String getColumnScooterId() {
        return COLUMN_SCOOTER_ID;
    }

    public static String getColumnScooterModel() {
        return COLUMN_SCOOTER_MODEL;
    }

    public static String getColumnScooterMaxVelocity() {
        return COLUMN_SCOOTER_MAX_VELOCITY;
    }

    public static String getColumnScooterColor() {
        return COLUMN_SCOOTER_COLOR;
    }

    public static String getColumnRentsBalance() {
        return COLUMN_RENTS_BALANCE;
    }

    public static String getColumnScooterAvailability() {
        return COLUMN_SCOOTER_AVAILABILITY;
    }

    public static String getColumnScooterBasket() {
        return COLUMN_SCOOTER_BASKET;
    }

    public static String getColumnScooterRange() {
        return COLUMN_SCOOTER_RANGE;
    }

    public static String getColumnScooterPrice() {
        return COLUMN_SCOOTER_PRICE;
    }

    public static String getColumnScooterBattery() {
        return COLUMN_SCOOTER_BATTERY;
    }

    public static String getColumnRentsId() {
        return COLUMN_RENTS_ID;
    }

    public static String getColumnRentsTimestamp() {
        return COLUMN_RENTS_TIMESTAMP;
    }

    public static String getColumnRentsIduser() {
        return COLUMN_RENTS_IDUSER;
    }

    public static String getColumnRentsIdscooter() {
        return COLUMN_RENTS_IDSCOOTER;
    }

    public static String getColumnReportsId() {
        return COLUMN_REPORTS_ID;
    }

    public static String getColumnReportsUserId() {
        return COLUMN_REPORTS_USER_ID;
    }

    public static String getColumnReportSubmissionDate() {
        return COLUMN_REPORT_SUBMISSION_DATE;
    }

    public static String getColumnReportsText() {
        return COLUMN_REPORTS_TEXT;
    }

    public static String getColumnReportsTitle() {
        return COLUMN_REPORTS_TITLE;
    }

    private static final String QUERY_USERS = "SELECT * FROM " + TABLE_USERS;

    public ResultSet queryUsers() throws SQLException{

        return queryUsers.executeQuery();
    }

    public ResultSet queryUser(int id) throws SQLException{

        queryUser.setInt(1,id);
        return queryUser.executeQuery();
    }

    private static final String QUERY_USER_BALANCE = " SELECT " + COLUMN_USER_ACC_BALANCE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ? " ;

    private static final String QUERY_USER_FUNDS = " SELECT " + COLUMN_USER_ACC_FUNDS + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ? " ;

    private static final String UPDATE_RENTS_BALANCE = " UPDATE " + TABLE_RENTS + " SET " + COLUMN_RENTS_BALANCE + " = " + " ? " + " WHERE " + COLUMN_RENTS_ID + " = ? ";

    private static final String UPDATE_USER_ACC_BALANCE = " UPDATE " + TABLE_USERS + " SET " +   COLUMN_USER_ACC_BALANCE +  " = " + " ( ? ) " + " WHERE " + COLUMN_USER_ID + " = ? ";

    private static final String UPDATE_USER_ACC_FUNDS = " UPDATE " + TABLE_USERS + " SET " +   COLUMN_USER_ACC_FUNDS +  " = " + " ( ? ) " + " WHERE " + COLUMN_USER_ID + " = ? ";

    private static final String QUERY_USER = " SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ? " ;

    private static final String QUERY_SCOOTER = " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_ID + " = ? " ;

    private static final String UPDATE_USER = " UPDATE " + TABLE_USERS + " SET " + " ( "+COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String UPDATE_SCOOTER = " UPDATE " + TABLE_SCOOTERS + " SET " + " ( "+COLUMN_SCOOTER_MODEL + ", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", " + COLUMN_SCOOTER_AVAILABILITY + ", " + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE + ", " + COLUMN_SCOOTER_PRICE + ", " +COLUMN_SCOOTER_BATTERY + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String UPDATE_SCOOTERSS = " UPDATE " + TABLE_SCOOTERS + " SET " + " ( "+ COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR +  ", "  + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_AVAILABILITY + ", "  + COLUMN_SCOOTER_RANGE + ", " + COLUMN_SCOOTER_PRICE + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String QUERY_ADMINS = " SELECT * FROM " + TABLE_ADMINS;

    private static final String QUERY_RENTS = " SELECT * FROM " + TABLE_RENTS;

    private static final String UPDATE_SCOOTER_AVAILABILITY = " UPDATE " + TABLE_SCOOTERS + " SET " +  COLUMN_SCOOTER_AVAILABILITY +   " = " + "  ?  " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String UPDATE_SCOOTER_BATTERY = " UPDATE " + TABLE_SCOOTERS + " SET " +  COLUMN_SCOOTER_BATTERY +   " = " + "  ?  " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String INSERT_INTO_RENTALS = " INSERT INTO " + TABLE_RENTS + " ( " + COLUMN_RENTS_TIMESTAMP + ", " + COLUMN_RENTS_IDUSER + ", " + COLUMN_RENTS_IDSCOOTER + " ) " +
            "VALUES ( ? , ? , ? )";
    private static final String INSERT_INTO_REPORTS = " INSERT INTO " + TABLE_REPORTS + " ( " + COLUMN_REPORTS_USER_ID + ", " + COLUMN_REPORT_SUBMISSION_DATE + ", " + COLUMN_REPORTS_TITLE + ", " + COLUMN_REPORTS_TEXT + ", " + COLUMN_REPORTS_DESTINATION + " ) " +
            "VALUES ( ? , ? , ? , ?, ?)";

    private static final String UPDATE_RETURN_DATE = " UPDATE " + TABLE_RENTS + " SET " +   COLUMN_RENTS_RETURN_DATE +  " = " + " ( ? ) " + " WHERE " + COLUMN_RENTS_ID + " = ? ";

    private static final String JOIN_SCOOTERS_ON_RENTALS = " SELECT " + TABLE_SCOOTERS+"." +COLUMN_SCOOTER_MODEL + ", " + TABLE_SCOOTERS +"."+COLUMN_SCOOTER_MAX_VELOCITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_COLOR + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_AVAILABILITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BASKET + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_RANGE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_PRICE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BATTERY + ", " + TABLE_RENTS+"."+COLUMN_RENTS_TIMESTAMP + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDUSER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_RETURN_DATE + ", " + TABLE_RENTS +"." + COLUMN_RENTS_ID + ", "  + TABLE_RENTS + "." + COLUMN_RENTS_BALANCE + " FROM " + TABLE_SCOOTERS + " INNER JOIN " + TABLE_RENTS + " ON " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_ID + " = " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + " WHERE "  + TABLE_RENTS+"."+COLUMN_RENTS_RETURN_DATE + " IS NULL " + " AND " + TABLE_RENTS + "." + COLUMN_RENTS_IDUSER + " = ? ";

    private static final String JOIN_SCOOTERS_HISTORY = " SELECT " + TABLE_SCOOTERS+"." +COLUMN_SCOOTER_MODEL + ", " + TABLE_SCOOTERS +"."+COLUMN_SCOOTER_MAX_VELOCITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_COLOR + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_AVAILABILITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BASKET + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_RANGE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_PRICE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BATTERY + ", " + TABLE_RENTS+"."+COLUMN_RENTS_TIMESTAMP + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDUSER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_RETURN_DATE + ", " + COLUMN_RENTS_BALANCE + " FROM " + TABLE_SCOOTERS + " INNER JOIN " + TABLE_RENTS + " ON " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_ID + " = " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + " WHERE " + TABLE_RENTS+"."+COLUMN_RENTS_RETURN_DATE + " IS NOT NULL " + " AND " + TABLE_RENTS + "." + COLUMN_RENTS_IDUSER + " = ? ";

    private static final String DELETE_FROM_RENTS = " DELETE FROM " + TABLE_RENTS + " WHERE " + COLUMN_SCOOTER_ID + " = " + " ? ";

    private static final String QUERY_RHISTORY = " SELECT * FROM " + TABLE_RHISTORY + " WHERE " + COLUMN_RENTS_IDUSER + " = " + " ? ";

    private static final String DELETE_FROM_USERS = " DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = " + " ? ";

    private static final String SELECT_VELOCITY =  " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_MAX_VELOCITY + " = ?";

    private static final String SELECT_COLOR = " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_COLOR + " = ?";

    private static final String SELECT_PRICE =  " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_PRICE + " = ?";

    private static final String SELECT_AVA =  " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_AVAILABILITY + " = ? ";

    private static final String SELECT_RANGE =  " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_RANGE + " BETWEEN " + " ? " + " AND " + " ? ";

    private static final String QUERY_REPORTS = " SELECT * FROM " + TABLE_REPORTS;

    private static final String DELETE_FROM_REPORTS = " DELETE FROM " + TABLE_REPORTS + " WHERE " + COLUMN_REPORTS_ID + " = ? ";

    private static final String QUERY_REPORTS_BY_ID = " SELECT * FROM " + TABLE_REPORTS + " WHERE " + COLUMN_REPORTS_ID + " = ? ";


    public ResultSet queryReportsByID(int reportID){

        try(PreparedStatement ps = c.prepareStatement(QUERY_REPORTS_BY_ID)){
            ps.setInt(1,reportID);
            return ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
    public void deleteFromReports(int reportID){

        try(PreparedStatement ps = c.prepareStatement(DELETE_FROM_REPORTS)){
            ps.setInt(1,reportID);
            ps.executeUpdate();
            c.commit();


        }catch (SQLException e){
            e.printStackTrace();


        }

    }
    private static final String QUERY_ALL_FROM_RHISTORY = " SELECT * FROM " + TABLE_RHISTORY;


    public ResultSet queryAllFromRhistory(){

        ResultSet rs;

        try(PreparedStatement ps = c.prepareStatement(QUERY_ALL_FROM_RHISTORY)){
            return rs =  ps.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;

    }


    public ResultSet joinScooterOnRentals(int userID) throws SQLException{

        joinScootersOnRentals.setInt(1,userID);
        return joinScootersOnRentals.executeQuery();
    }

    public ResultSet joinScooterHistory(int userID) throws SQLException{

        joinScootersHistory.setInt(1,userID);
        return joinScootersHistory.executeQuery();
    }

    public ResultSet queryReports() throws SQLException{

        try(PreparedStatement ps = c.prepareStatement(QUERY_REPORTS)){

            return ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet queryAdmins() throws SQLException{

        return queryAdmins.executeQuery();
    }

    private static final String QUERY_SCOOTERS = " SELECT * FROM " + TABLE_SCOOTERS;

    public ResultSet queryScooters() throws SQLException{
        return queryScooters.executeQuery();
    }

    public ResultSet queryScooter(int scooterID) throws SQLException{

        queryScooter.setInt(1,scooterID);
        return queryScooter.executeQuery();
    }

    public ResultSet queryRents() throws SQLException{

        return queryRents.executeQuery();
    }

    public ResultSet queryUserBalance(int userID) throws SQLException{
        queryUserBalance.setInt(1,userID);
        return  queryUserBalance.executeQuery();
    }

    public ResultSet queryUserFunds(int userID) throws SQLException{
        queryUserFunds.setInt(1,userID);
        return  queryUserFunds.executeQuery();
    }

    public void updateUserAccBalance(double amount, int userID){

        try {
            updateUserAccBalance.setDouble(1, amount);
            updateUserAccBalance.setInt(2, userID);
            updateUserAccBalance.executeUpdate();
            c.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void UpdateUserAccFunds(double amount, int userID){

        try {
            updateUserAccFunds.setDouble(1, amount);
            updateUserAccFunds.setInt(2, userID);
            updateUserAccFunds.executeUpdate();
            c.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet queryRHistory(int userID) throws SQLException{

        queryRhistory.setInt(1,userID);
        return queryRhistory.executeQuery();
    }


    private final String INSERT_INTO_USERS = " INSERT INTO " + TABLE_USERS + " ( "  + COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + ", " + COLUMN_USER_ACC_BALANCE + ", " + COLUMN_USER_ACC_FUNDS + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?, ?)";

    private final String INSERT_INTO_SCOOTERS =" INSERT INTO " + TABLE_SCOOTERS + " ( " + COLUMN_SCOOTER_MODEL +", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", " + COLUMN_SCOOTER_AVAILABILITY + ", " + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE +", "+ COLUMN_SCOOTER_PRICE + ", " + COLUMN_SCOOTER_BATTERY + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?, ? )";

    private final String INSERT_INTO_RHISTORY =" INSERT INTO " + TABLE_RHISTORY + " ( " + COLUMN_SCOOTER_MODEL + ", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", "  + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE +", "+ COLUMN_SCOOTER_PRICE + ", " + COLUMN_SCOOTER_BATTERY + ", " + COLUMN_RENTS_IDUSER + ", " + COLUMN_RENTS_TIMESTAMP + ", " + COLUMN_RENTS_RETURN_DATE + "," + COLUMN_RENTS_BALANCE + ", " + COLUMN_SCOOTER_ID + ", " + COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?, ? , ? , ? , ?, ?, ?, ? )";


    private final String DELETE_SCOOTER = " DELETE FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_ID + " = " + " ? ";

    private final String DELETE_FROM_RHISTORY = " DELETE FROM " + TABLE_RHISTORY + " WHERE " + COLUMN_RENTS_IDUSER + " = " + " ? ";

    public ResultSet selectAva(int number) throws SQLException{

        selectAvaliability.setInt(1, number);
        return selectAvaliability.executeQuery();
    }


    public ResultSet selectColor(String color) throws SQLException{

        selectColor.setString(1, color);
        return selectColor.executeQuery();
    }

    public ResultSet selectPrice(double price) throws SQLException{

        selectPrice.setDouble(1, price);
        return selectPrice.executeQuery();

    }

    public ResultSet selectRange(int range1,int range2) throws SQLException{

        selectRange.setInt(1,range1);
        selectRange.setInt(2,range2);
        return selectRange.executeQuery();
    }

    public void deleteFromUsers(int userID){

        try{
            deleteFromUsers.setInt(1,userID);
            deleteFromUsers.executeUpdate();
            c.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void insertIntoRhistory(ScooterJoin sj,User user){

        try {
            insertIntoRhistory.setString(1, sj.getScooterModel());
            insertIntoRhistory.setInt(2, sj.getScooterMaxVelocity());
            insertIntoRhistory.setString(3,sj.getScooterColor());
            insertIntoRhistory.setInt(4,sj.getScooterBasket());
            insertIntoRhistory.setInt(5,sj.getScooterRange());
            insertIntoRhistory.setDouble(6,sj.getScooterPrice());
            insertIntoRhistory.setInt(7,sj.getScooterBattery());
            insertIntoRhistory.setInt(8,sj.getUserID());
            insertIntoRhistory.setTimestamp(9,sj.getRentalTime());
            insertIntoRhistory.setTimestamp(10,sj.getReturnDate());
            insertIntoRhistory.setDouble(11,sj.getBalance());
            insertIntoRhistory.setInt(12,sj.getRentsScooterID());
            insertIntoRhistory.setString(13,user.getUserName());
            insertIntoRhistory.setString(14,user.getUserSurname());
            insertIntoRhistory.executeUpdate();
            c.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void insertIntoRentals(Rent rent){

        try {

            insertIntoRentals.setTimestamp(1,rent.getRentalTime());
            insertIntoRentals.setInt(2,rent.getUserID());
            insertIntoRentals.setInt(3,rent.getRentScooterID());
            insertIntoRentals.executeUpdate();
            c.commit();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void insertIntoReports(Report report){

        try(PreparedStatement ps = c.prepareStatement(INSERT_INTO_REPORTS)){

            ps.setInt(1,report.getUserID());
            ps.setTimestamp(2,report.getSubmissionDate());
            ps.setString(3,report.getReportTitle());
            ps.setString(4,report.getReportText());
            ps.setInt(5,report.getDestination());
            ps.executeUpdate();
            c.commit();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updateUser(User user){

        try {

            updateUsers.setString(1, user.getUserName());
            updateUsers.setString(2,user.getUserSurname());
            updateUsers.setString(3,user.getUserLogin());
            updateUsers.setString(4,user.getUserPassword());
            updateUsers.setString(5,user.getUserEmail());
            updateUsers.setString(6,user.getUserCardNo());
            updateUsers.setInt(7,user.getUserId());
            updateUsers.executeUpdate();
            c.commit();

        }catch(SQLException e){

            e.printStackTrace();

        }

    }

    public void updateRentsBalance (double balance,int rentsID) {

        try {

            updateRentsBalance.setDouble(1,balance);
            updateRentsBalance.setInt(2,rentsID);
            updateRentsBalance.executeUpdate();
            c.commit();


        }catch(SQLException e){

            e.printStackTrace();
        }

    }

    public void updateScooterss(Scooter scooter){

        try {

            updateScooterss.setInt(1,scooter.getScooterMaxVelocity());
            updateScooterss.setString(2,scooter.getScooterColor());
            updateScooterss.setInt(3,scooter.getScooterBasket());
            updateScooterss.setInt(4,scooter.getScooterAvailability());
            updateScooterss.setInt(5,scooter.getScooterRange());
            updateScooterss.setDouble(6,scooter.getScooterPrice());
            updateScooterss.setInt(7,scooter.getScooterID());
            updateScooterss.executeUpdate();
            c.commit();

        }catch(SQLException e){

            e.printStackTrace();

        }

    }

    public void updateReturnDate(Timestamp tsp, int rentsID){

        try {
            updateReturnDate.setTimestamp(1, tsp);
            updateReturnDate.setInt(2,rentsID);
            updateReturnDate.executeUpdate();
            c.commit();

        }catch (SQLException e){
            e.printStackTrace();

        }    }

    public void updateScooterAvailability(Scooter scooter){

        try{

            updateScooterAvailability.setInt(1,scooter.getScooterAvailability());
            updateScooterAvailability.setInt(2,scooter.getScooterID());
            updateScooterAvailability.executeUpdate();
            c.commit();

        }catch(SQLException e){

            e.printStackTrace();
        }

    }
    public void updateScooterBattery(Scooter scooter){

        try{

            updateScooterBattery.setInt(1,scooter.getScooterBattery());
            updateScooterBattery.setInt(2,scooter.getScooterID());
            updateScooterBattery.executeUpdate();
            c.commit();

        }catch(SQLException e){

            e.printStackTrace();
        }

    }
    public void insertUsers(User user){

        try {
            c.setAutoCommit(false);
            insertIntoUsers.setString(1, user.getUserName());
            insertIntoUsers.setString(2,user.getUserSurname());
            insertIntoUsers.setString(3, user.getUserLogin());
            insertIntoUsers.setString(4, user.getUserPassword());
            insertIntoUsers.setString(5, user.getUserEmail());
            insertIntoUsers.setString(6, user.getUserCardNo());
            insertIntoUsers.setDouble(7, user.getUserAccountBalance());
            insertIntoUsers.setDouble(8, user.getUserAccountFunds());
            int affectedRows =  insertIntoUsers.executeUpdate();
            if(affectedRows==1) {
                c.commit();
            }else{
                throw new SQLException("The user insertion failed");
            }
        }catch(SQLException e1){
            try{
                System.out.println("Rollback");
                c.rollback();
            }catch(SQLException e2){
                System.out.println("Rollback failed");
                e2.printStackTrace();
            }
        }
    }

    public boolean insertScooters(Scooter scooter){


        try {
            c.setAutoCommit(false);
            insertIntoScooters.setString(1, scooter.getScooterModel());
            insertIntoScooters.setInt(2, scooter.getScooterMaxVelocity());
            insertIntoScooters.setString(3, scooter.getScooterColor());
            insertIntoScooters.setInt(4, scooter.getScooterAvailability());
            insertIntoScooters.setInt(5, scooter.getScooterBasket());
            insertIntoScooters.setInt(6, scooter.getScooterRange());
            insertIntoScooters.setDouble(7, scooter.getScooterPrice());
            insertIntoScooters.setInt(8,scooter.getScooterBattery());
            int affectedRows =  insertIntoScooters.executeUpdate();
            if(affectedRows == 1){

                c.commit();
                return true;

            }else{

                throw new SQLException("The scooter insertion failed");

            }
        }catch(SQLException e1){

            try{

                System.out.println("Rollback");
                c.rollback();

            }catch(SQLException e2){

                System.out.println("Rollback failed");
                e2.printStackTrace();

            }

        }
        return false;
    }



    public void deleteFromRhistory (int userID) {

        try {

            deleteFromRhistory.setInt(1,userID);
            deleteFromRhistory.executeUpdate();
            c.commit();


        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    public void deleteScooter (int scooterId) {

        try {
            c.setAutoCommit(false);
            deleteFromScooters.setInt(1,scooterId);
            deleteFromScooters.executeUpdate();
            c.commit();


        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    public void open () {

        try{
            c = DriverManager.getConnection(CONNECTION_STRING, "postgres", "haslo");
            Statement stm = c.createStatement();
            c.setAutoCommit(false);
            stm.executeUpdate(CREATE_USERS_TABLE);
            stm.executeUpdate(CREATE_ADMINS_TABLE);
            stm.executeUpdate(CREATE_SCOOTERS_TABLE);
            stm.executeUpdate(CREATE_RENTS_TABLE);
            stm.executeUpdate(CREATE_RHISTORY_TABLE);
            stm.executeUpdate(CREATE_REPORTS_TABLE);
            c.commit();
            stm.close();

            insertIntoUsers = c.prepareStatement(INSERT_INTO_USERS);
            insertIntoScooters = c.prepareStatement(INSERT_INTO_SCOOTERS);
            deleteFromScooters = c.prepareStatement(DELETE_SCOOTER);
            queryUsers = c.prepareStatement(QUERY_USERS);
            queryAdmins = c.prepareStatement(QUERY_ADMINS);
            queryScooters = c.prepareStatement(QUERY_SCOOTERS);
            updateUsers = c.prepareStatement(UPDATE_USER);
            queryUser = c.prepareStatement(QUERY_USER);
            queryRents = c.prepareStatement(QUERY_RENTS);
            updateScooters = c.prepareStatement(UPDATE_SCOOTER);
            updateScooterAvailability = c.prepareStatement(UPDATE_SCOOTER_AVAILABILITY);
            updateScooterBattery = c.prepareStatement(UPDATE_SCOOTER_BATTERY);
            insertIntoRentals = c.prepareStatement(INSERT_INTO_RENTALS);
            joinScootersOnRentals = c.prepareStatement(JOIN_SCOOTERS_ON_RENTALS);
            joinScootersHistory = c.prepareStatement(JOIN_SCOOTERS_HISTORY);
            queryScooter = c.prepareStatement(QUERY_SCOOTER);
            updateScooterss = c.prepareStatement(UPDATE_SCOOTERSS);
            updateReturnDate = c.prepareStatement(UPDATE_RETURN_DATE);
            insertIntoRhistory = c.prepareStatement(INSERT_INTO_RHISTORY);
            queryRhistory = c.prepareStatement(QUERY_RHISTORY);
            deleteFromRhistory = c.prepareStatement(DELETE_FROM_RHISTORY);
            updateRentsBalance = c.prepareStatement(UPDATE_RENTS_BALANCE);
            updateUserAccBalance = c.prepareStatement(UPDATE_USER_ACC_BALANCE);
            updateUserAccFunds = c.prepareStatement(UPDATE_USER_ACC_FUNDS);
            queryUserBalance = c.prepareStatement(QUERY_USER_BALANCE);
            queryUserFunds = c.prepareStatement(QUERY_USER_FUNDS);
            deleteFromUsers = c.prepareStatement(DELETE_FROM_USERS);
            selectAvaliability = c.prepareStatement(SELECT_AVA);
            selectMaxVelocity = c.prepareStatement(SELECT_VELOCITY);
            selectColor = c.prepareStatement(SELECT_COLOR);
            selectRange = c.prepareStatement(SELECT_RANGE);
            selectPrice = c.prepareStatement(SELECT_PRICE);

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    public void close(){

        try{
            insertIntoUsers.close();
            insertIntoScooters.close();
            deleteFromScooters.close();
            queryUsers.close();
            queryAdmins.close();
            queryScooters.close();
            updateUsers.close();
            queryUser.close();
            queryRents.close();
            updateScooters.close();
            updateScooterss.close();
            updateScooterAvailability.close();
            updateScooterBattery.close();
            insertIntoRentals.close();
            joinScootersOnRentals.close();
            joinScootersHistory.close();
            queryScooter.close();
            updateReturnDate.close();
            deleteFromUsers.close();
            updateUserAccBalance.close();
            updateUserAccFunds.close();
            queryUserBalance.close();
            queryUserFunds.close();
            insertIntoRhistory.close();
            queryRhistory.close();
            deleteFromRhistory.close();
            c.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

