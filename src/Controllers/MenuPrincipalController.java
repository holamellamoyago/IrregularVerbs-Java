package Controllers;

import java.io.File;

import Service.NavigationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController implements NavigationService{
    @FXML
    private Button btnVerbs;

    @FXML
    private Button btnVocabulary;

    @FXML
    private void changeScreen(){
        cambiarPantalla("IrregularVerbsScreen.fxml");
    }


}
