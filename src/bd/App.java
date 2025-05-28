package bd;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class App {
        public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ApuntesDB db = new ApuntesDB();
        int opcionEscogida = -1;
        
        do {
            System.out.println("**************");
            System.out.println("** APPuntes **");
            System.out.println("**************");

            System.out.println("Escribe la opcion que quieres hacer: ");
            System.out.println("1.Iniciar sesisión");
            System.out.println("2.Registrarse en la plataforma");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Subir apuntes");
            System.out.println("5. Subir ejercicio.");
            System.out.println("----------------------------------------");
            System.out.println("Elige la opcion");
             opcionEscogida = sc.nextInt();
            switch (opcionEscogida) {
                case 1 -> iniciarSesion();
                case 2 -> registroUsuario();
                case 3 -> listarUsuarios();
                case 4 -> ApuntesDB.crearApunte();
                case 5 -> ApuntesDB.crearEjercicio();
            
                default -> System.out.println("Error");
            }
        } while (opcionEscogida>=1 && opcionEscogida<3);
    }


        public static boolean registroUsuario() {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            System.out.print("Usuario: ");
            String usuario = System.console().readLine();
            System.out.print("Contraseña: ");
            String password = new String(System.console().readPassword());
            int resultado = sentencia.executeUpdate("INSERT INTO user (username, password) VALUES ('" + usuario + "', '"
                    + generarStringHash2Y(password) + "')");
            sentencia.close();
            conexion.close();
            return resultado == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Error al crear el usuario");
            return false;
        }
    }

        /**
     * Lista los usuarios de la base de datos
     */
    public static void listarUsuarios() {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT * FROM user");

            while (resultado.next()) {
                // Procesa los datos
                int id = resultado.getInt("id");
                String username = resultado.getString("username");
                //String password = resultado.getString("password");
                Timestamp createdAt = resultado.getTimestamp("create_up");

                // Procesa los datos
                System.out.println(
                        "ID: " + id + ", username: " + username + ", createdAt: " + createdAt);
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprueba si un usuario y contraseña son correctos
     * 
     * @param username Usuario
     * @param password Contraseña
     * @return true si el usuario y contraseña son correctos
     */
    public static boolean loginUsuario(String username, String password) {
        boolean loginOk = false;
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT * FROM user WHERE username LIKE '" + username + "'");

            if (resultado.next()) {
                // Si existe el usuario valida la contraseña con BCrypt
                byte[] passwordHashed = resultado.getString("password").getBytes(StandardCharsets.UTF_8);
                BCrypt.Result resultStrict = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verifyStrict(
                        password.getBytes(StandardCharsets.UTF_8),
                        passwordHashed);
                loginOk = resultStrict.verified;
                loginOk = validarHash2Y(password, resultado.getString("password"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginOk;
    }

    /**
     * Cambia la contraseña de un usuario
     * 
     * @param username Usuario
     * @param password Nueva contraseña
     * @return true si se cambió la contraseña
     */
    public static boolean cambiarPassword(String username, String password) {
        boolean cambiarPassword = false;
        Connection conexion = Conexion.conectar();

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            int resultado = sentencia.executeUpdate("UPDATE user SET password='" + generarStringHash2Y(password)
                    + "' WHERE username LIKE '" + username + "'");

            if (resultado == 1) {
                // Si se cambió la contraseña
                cambiarPassword = true;
            }

            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cambiarPassword;
    }

    /*
     * FUNCIONES BCRYPT: generar hash y validar hash
     */
    /**
     * Valida un hash de BCrypt
     * 
     * @param password Contraseña en texto claro
     * @param hash2y   Hash de BCrypt
     * @return true si la contraseña es correcta
     */
    private static boolean validarHash2Y(String password, String hash2y) {
        return BCrypt.verifyer(BCrypt.Version.VERSION_2Y)
                .verifyStrict(password.getBytes(StandardCharsets.UTF_8),
                        hash2y.getBytes(StandardCharsets.UTF_8)).verified;
    }

    /**
     * Genera un hash de BCrypt
     * 
     * @param password Contraseña en texto claro
     * @return Hash de BCrypt
     */
    private static String generarStringHash2Y(String password) {
        char[] bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToChar(13, password.toCharArray());
        return String.valueOf(bcryptChars);
    }

    /**
     * Inicia sesión de usuario
     * Solicita credenciales de inicio de sesión, y si son correctas devuelve el
     * nombre de usuario.
     * 
     * @return Usuario que ha iniciado sesión
     */
    public static String iniciarSesion() {
        do {
            System.out.println("LOGIN DE USUARIO");
            System.out.print("Usuario: ");
            String usuario = System.console().readLine();
            System.out.print("Contraseña: ");
            String password = new String(System.console().readPassword());
            if (loginUsuario(usuario, password)) {
                return usuario;
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } while (true);
    }
}
