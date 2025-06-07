package Controllers;

import java.io.File;

import Service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController implements NavigationService{
    @FXML
    private Button btnVerbs;

    @FXML
    private Button btnVocabulary;

    @FXML
    private void changeScreen(ActionEvent e){
        Button btnClickeado = (Button) e.getSource();
        System.out.println(btnClickeado.getText());

        switch (btnClickeado.getText()) {
            case "Irregular Verbs"  -> cambiarPantalla("IrregularVerbsScreen.fxml");
            case "Vocabulary B1" -> cambiarPantalla("WordScreen.fxml");
        }
    }


}
