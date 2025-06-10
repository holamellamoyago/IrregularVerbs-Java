package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Database.ConnectionMySQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController{

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    private void login(){
        String email = fieldEmail.getText();
        String pwd 
    }


}
