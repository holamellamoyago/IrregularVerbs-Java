package Model;

public class User {
    private int codigo;
    private String name, username, password, surname;
    
    public User(int codigo, String name, String username, String password, String surname) {
        this.codigo = codigo;
        this.name = name;
        this.username = username;
        this.password = password;
        this.surname = surname;
    }

    
}
