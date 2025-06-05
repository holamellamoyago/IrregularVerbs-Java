package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL {

    public static final String user = "root";
    public static final String pwd = "";
    public static final String url = "jdbc:mysql://localhost/irregularverbs";

    public static void main(String[] args) {
        try {
            Connection conex = DriverManager.getConnection(url, user, pwd);
            System.out.println("Si se conecto correctamente");
        } catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
    }
}
