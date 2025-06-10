package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ConnectionMySQL {

    private static final String user = "root";
    private static final String pwd = "";
    private static final String url = "jdbc:mysql://localhost/learnenglish";
    public static Connection conexionDB;

    public static boolean initiateDatabase(){
        try {
            conexionDB = DriverManager.getConnection(url, user, pwd);
            System.out.println("DB Connected");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void login(String username , String pwd){

    }
}
