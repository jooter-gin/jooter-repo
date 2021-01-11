package sample;

public class User extends Validate {

    private int userId;
    private String userName;
    private String userSurname;
    private String userLogin;
    private String userPassword;
    private String userEmail;
    private String userCardNo;
    private double userAccountBalance;

    public User(int userId, String userName, String userSurname, String userLogin, String userPassword, String userEmail, String userCardNo, double userAccountBalance) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userCardNo = userCardNo;
        this.userAccountBalance = userAccountBalance;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCardNo() {
        return userCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }

    public double getUserAccountBalance() {
        return userAccountBalance;
    }

    public void setUserAccountBalance(double userAccountBalance) {
        this.userAccountBalance = userAccountBalance;
    }




}