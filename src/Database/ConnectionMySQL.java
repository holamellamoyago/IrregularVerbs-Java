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

    // public static void main(String[] args) {
    //     try {
    //         conexionDB = DriverManager.getConnection(url, user, pwd);
    //         String query = "select * from users";
    //         PreparedStatement querySelect = conexionDB.prepareStatement(query);
    //         ResultSet resultado = querySelect.executeQuery();

    //         System.out.println(resultado.toString());

    //         while (resultado.next()) {
    //             String nombre = resultado.getString("name");
    //             System.out.println(nombre);
    //         }

    //     } catch (Exception e) {
    //         System.out.println("Error en la conexión");
    //         e.printStackTrace();
    //     }
    // }

    public static boolean initiateDatabase(){
        try {
            conexionDB = DriverManager.getConnection(url, user, pwd);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void login(String username , String pwd){

    }
}
