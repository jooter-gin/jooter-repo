package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration {


    public static void registerUser(User user){

        DataSource.getInstance().insertUsers(user);

    }

    public static boolean checkIfUserExists(String login,String email){

        try {
            ResultSet rs = DataSource.getInstance().queryUsers();
            ResultSet rsAdmin = DataSource.getInstance().queryAdmins();
            while(rs.next()){
                if(rs.getString(DataSource.getColumnUserLogin()).equals(login)  || rs.getString(DataSource.getColumnUserEmail()).equals(email)){
                    return true;
                }
            }
            while(rsAdmin.next()){
                if(rsAdmin.getString(DataSource.getColumnAdminLogin()).equals(login) || rsAdmin.getString(DataSource.getColumnUserEmail()).equals(email)){
                    return true;
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
