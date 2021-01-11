package sample;

import java.sql.*;

public final class DataSource {

    private static final String DB_NAME = "zchmtson";
//
   private static final String CONNECTION_STRING = "jdbc:postgresql://hattie.db.elephantsql.com:5432/" + DB_NAME;

    //private static final String DB_NAME = "jooterExample";

   // private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/" + DB_NAME;

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

    private PreparedStatement insertIntoRentals;

    private PreparedStatement joinScootersOnRentals;

    private PreparedStatement queryScooter;

    private PreparedStatement updateReturnDate;

    private PreparedStatement deleteFromRentals;


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

    public static String getColumnRentsID() {return COLUMN_RENTS_ID;}

    private final String CREATE_RENTS_TABLE = " CREATE TABLE IF NOT EXISTS " + " " + TABLE_RENTS +
            "( " +
            COLUMN_RENTS_ID + " SERIAL PRIMARY KEY, " +
           // COLUMN_RENTS_RENTALDATE + " DATE NOT NULL, " +
            COLUMN_RENTS_TIMESTAMP + " TIMESTAMP NOT NULL , " +
            COLUMN_RENTS_RETURN_DATE + " TIMESTAMP , " +
            COLUMN_RENTS_IDUSER + " INT, "  +
            COLUMN_RENTS_IDSCOOTER + " INT, "  +
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

    private static final String QUERY_USERS = "SELECT * FROM " + TABLE_USERS;

    public ResultSet queryUsers() throws SQLException{

        return queryUsers.executeQuery();
    }

    public ResultSet queryUser(int id) throws SQLException{

        queryUser.setInt(1,id);
        return queryUser.executeQuery();
    }

    private static final String QUERY_USER = " SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ? " ;

    private static final String QUERY_SCOOTER = " SELECT * FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_ID + " = ? " ;

    private static final String UPDATE_USER = " UPDATE " + TABLE_USERS + " SET " + " ( "+COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String UPDATE_SCOOTER = " UPDATE " + TABLE_SCOOTERS + " SET " + " ( "+COLUMN_SCOOTER_MODEL + ", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", " + COLUMN_SCOOTER_AVAILABILITY + ", " + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE + ", " + COLUMN_SCOOTER_PRICE + ", " +COLUMN_SCOOTER_BATTERY + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String UPDATE_SCOOTERSS = " UPDATE " + TABLE_SCOOTERS + " SET " + " ( "+ COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR +  ", "  + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_AVAILABILITY + ", "  + COLUMN_SCOOTER_RANGE + ", " + COLUMN_SCOOTER_PRICE + " ) " + " = " + " ( ? , ? , ? , ? , ? , ? ) " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String QUERY_ADMINS = " SELECT * FROM " + TABLE_ADMINS;

    private static final String QUERY_RENTS = " SELECT * FROM " + TABLE_RENTS;

    private static final String UPDATE_SCOOTER_AVAILABILITY = " UPDATE " + TABLE_SCOOTERS + " SET " +  COLUMN_SCOOTER_AVAILABILITY +   " = " + "  ?  " + " WHERE " + COLUMN_SCOOTER_ID + " = ? ";

    private static final String INSERT_INTO_RENTALS = " INSERT INTO " + TABLE_RENTS + " ( " + COLUMN_RENTS_TIMESTAMP + ", " + COLUMN_RENTS_IDUSER + ", " + COLUMN_RENTS_IDSCOOTER + " ) " +
            "VALUES ( ? , ? , ? )";

    private static final String UPDATE_RETURN_DATE = " UPDATE " + TABLE_RENTS + " SET " +   COLUMN_RENTS_RETURN_DATE +  " = " + " ( ? ) " + " WHERE " + COLUMN_RENTS_ID + " = ? ";


    private static final String JOIN_SCOOTERS_ON_RENTALS = " SELECT " + TABLE_SCOOTERS+"." +COLUMN_SCOOTER_MODEL + ", " + TABLE_SCOOTERS +"."+COLUMN_SCOOTER_MAX_VELOCITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_COLOR + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_AVAILABILITY + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BASKET + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_RANGE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_PRICE + ", " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_BATTERY + ", " + TABLE_RENTS+"."+COLUMN_RENTS_TIMESTAMP + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDUSER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + ", " + TABLE_RENTS+"."+COLUMN_RENTS_RETURN_DATE + ", " + TABLE_RENTS +"." + COLUMN_RENTS_ID  + " FROM " + TABLE_SCOOTERS + " INNER JOIN " + TABLE_RENTS + " ON " + TABLE_SCOOTERS+"."+COLUMN_SCOOTER_ID + " = " + TABLE_RENTS+"."+COLUMN_RENTS_IDSCOOTER + " WHERE "  + TABLE_RENTS + "." + COLUMN_RENTS_IDUSER + " = ? ";

    private static final String DELETE_FROM_RENTS = " DELETE FROM " + TABLE_RENTS + " WHERE " + COLUMN_SCOOTER_ID + " = " + " ? ";

    public ResultSet joinScooterOnRentals(int userID) throws SQLException{

        joinScootersOnRentals.setInt(1,userID);
        return joinScootersOnRentals.executeQuery();
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


    private final String INSERT_INTO_USERS = " INSERT INTO " + TABLE_USERS + " ( "  + COLUMN_USER_NAME + ", " + COLUMN_USER_SURNAME + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + ", " + COLUMN_USER_EMAIL + ", " + COLUMN_USER_CARD_NO + ", " + COLUMN_USER_ACC_BALANCE + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?)";

    private final String INSERT_INTO_SCOOTERS =" INSERT INTO " + TABLE_SCOOTERS + " ( " + COLUMN_SCOOTER_MODEL +", " + COLUMN_SCOOTER_MAX_VELOCITY + ", " + COLUMN_SCOOTER_COLOR + ", " + COLUMN_SCOOTER_AVAILABILITY + ", " + COLUMN_SCOOTER_BASKET + ", " + COLUMN_SCOOTER_RANGE +", "+ COLUMN_SCOOTER_PRICE + ", " + COLUMN_SCOOTER_BATTERY + " ) " +
            "VALUES ( ? , ? , ? , ?, ?, ?, ?, ? )";


    private final String DELETE_SCOOTER = " DELETE FROM " + TABLE_SCOOTERS + " WHERE " + COLUMN_SCOOTER_ID + " = " + " ? ";




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


    public void deleteFromRentals (ScooterJoin sj) {

        try {
            c.setAutoCommit(false);
            deleteFromRentals.setInt(1,sj.getRentsScooterID());
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
            c = DriverManager.getConnection(CONNECTION_STRING, "zchmtson", "eidFBsA6ftUlntzXqXBjWrnBwEuXra3h");
            Statement stm = c.createStatement();
            c.setAutoCommit(false);
            stm.executeUpdate(CREATE_USERS_TABLE);
            stm.executeUpdate(CREATE_ADMINS_TABLE);
            stm.executeUpdate(CREATE_SCOOTERS_TABLE);
            stm.executeUpdate(CREATE_RENTS_TABLE);
            c.commit();

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
            insertIntoRentals = c.prepareStatement(INSERT_INTO_RENTALS);
            joinScootersOnRentals = c.prepareStatement(JOIN_SCOOTERS_ON_RENTALS);
            queryScooter = c.prepareStatement(QUERY_SCOOTER);
            updateScooterss = c.prepareStatement(UPDATE_SCOOTERSS);
            updateReturnDate = c.prepareStatement(UPDATE_RETURN_DATE);
            deleteFromRentals = c.prepareStatement(DELETE_FROM_RENTS);

            stm.close();


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
            insertIntoRentals.close();
            joinScootersOnRentals.close();
            queryScooter.close();
            updateReturnDate.close();
            deleteFromRentals.close();
            c.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

