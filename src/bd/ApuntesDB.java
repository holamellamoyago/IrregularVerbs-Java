package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ApuntesDB {
    

    public static boolean crearInstituto(String nombre, int estudiantes){
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO Instituto (nombre,numEstudiantes) VALUES ('"
                    + nombre + "', '" + estudiantes + "')");
            sentencia.close();
            conexion.close();
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        } 
    }
    public static boolean crearCiclo(String nombre){
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO ciclo (nombre,id_instituto) VALUES ('" 
            + nombre + "', '1')");

            sentencia.close();
            conexion.close();
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        } 
    }

    public static boolean crearAsignatura(String nombre){
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO asignatura (nombre) VALUES ('" 
            + nombre + "')");

            


            sentencia.close();
            conexion.close();
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        } 
    }

    public static boolean crearEjercicio() {
        Connection conexion = Conexion.conectar();
        Statement sentencia;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Dime nombre de apunte: ");
        String nombreEjercicio = sc.nextLine();

        System.out.println("Dime nombre de instituto: ");
        String instituto = sc.nextLine();

        System.out.println("Dime nombre de ciclo: ");
        String ciclo = sc.nextLine();

        System.out.println("Dime nombre de asignatura: ");
        String asignatura = sc.nextLine();






        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO ejercicio (usuario,nombreEjercicio,instituto,ciclo,asignatura ) VALUES ('"
                    + usuario + "', '" + nombreEjercicio + "', '" + instituto + "', '" + ciclo + "', '" + asignatura + "')");
            sentencia.close();
            conexion.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean crearApunte() {
        Connection conexion = Conexion.conectar();
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Dime nombre de apunte: ");
        String nombreApunte = sc.nextLine();

        System.out.println("Dime nombre de instituto: ");
        String instituto = sc.nextLine();

        System.out.println("Dime nombre de ciclo: ");
        String ciclo = sc.nextLine();

        System.out.println("Dime nombre de asignatura: ");
        String asignatura = sc.nextLine();
        


        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO apunte (usuario,nombreApunte,instituto,ciclo,asignatura ) VALUES ('"
                    + usuario + "', '" + nombreApunte + "', '" + instituto + "', '" + ciclo + "', '" + asignatura + "')");
            sentencia.close();
            conexion.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        // System.out.println(crearEjercicio());
        System.out.println(crearApunte());
    }
    
}
