package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public static int CheckLogin(String login, String password){


        try {
            ResultSet rsUser = DataSource.getInstance().queryUsers();
            ResultSet rsAdmin = DataSource.getInstance().queryAdmins();
            while(rsUser.next()){
                if(rsUser.getString(DataSource.getColumnUserLogin()).equals(login)){
                    if(rsUser.getString(DataSource.getColumnUserPassword()).equals(password)) {
                        return Integer.parseInt(rsUser.getString(DataSource.getColumnUserID()));
                    }
                }
            }
            while (rsAdmin.next()){
                if(rsAdmin.getString(DataSource.getColumnAdminLogin()).equals(login))
                    if(rsAdmin.getString(DataSource.getColumnAdminPassword()).equals(password)) {
                        return Integer.parseInt(rsAdmin.getString(DataSource.getColumnAdminID()))-100;  //-100 zeby odroznic adminow od userow xd
                    }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
