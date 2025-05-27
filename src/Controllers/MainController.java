package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import App.AppVerbos;
import Model.IrregularVerb;
import Model.Util;
import Service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class MainController implements Initializable, NavigationService {

    @FXML
    private Label lblText;

    @FXML
    private TextField txtFieldParticiiple;

    @FXML
    private TextField txtFieldSimple;

    @FXML
    private TextField txtFieldTraduccion;

    @FXML
    private Button buttonCheck;

    @FXML
    Label txtMessage;

    @FXML
    MenuItem importarfichero;

    List<IrregularVerb> verbs;
    IrregularVerb irVrb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verbs = Util.readVerbs();
        if (verbs != null && !verbs.isEmpty()) {
            irVrb = IrregularVerb.getVb(verbs);

            lblText.setText(irVrb.getInfinitive());
        }
    }

    public void checkVerb(ActionEvent e) {
        System.out.println("Escrito: " + txtFieldSimple.getText());
        System.out.println("Correcto: " + irVrb.getSimple());
        int contador = 0;

        String message = "Corrige tu error: ";

        System.out.println(e.getSource().toString());

        if (txtFieldSimple.getText().toLowerCase().equals(irVrb.getSimple().toLowerCase())) {
            txtFieldSimple.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldSimple.setStyle("-fx-background-color:red;");
            message += "simple ";
        }

        if (txtFieldParticiiple.getText().toLowerCase().equals(irVrb.getParticiple().toLowerCase())) {
            txtFieldParticiiple.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldParticiiple.setStyle("-fx-background-color:red;");
            message += "participle ";
        }

        if (txtFieldTraduccion.getText().toLowerCase().equals(irVrb.getTranslation().toLowerCase())) {
            txtFieldTraduccion.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldTraduccion.setStyle("-fx-background-color:red;");
            message += "traducción ";
        }

        if (contador < 3) {
            txtMessage.setText(message);
        } else {
            txtMessage.setText("Enhorabuena , +1 punto");
            cambiarPantalla("../resources/MainController.fxml");
        }

    }



    @FXML
    public void importarVerbos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        StringBuilder fileContent = new StringBuilder();
        ExtensionFilter filter =  new ExtensionFilter("Debes importarlo en formato JSON", "*.json");
        Stage stage = (Stage) buttonCheck.getScene().getWindow();
        Gson gson = new Gson();

        fileChooser.setTitle("Importar JSON...");
        fileChooser.setInitialDirectory(new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu"));
        fileChooser.getExtensionFilters().add(filter);

        File selectedFile = fileChooser.showOpenDialog(stage);

        try (BufferedReader buffer = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String json = fileContent.toString();
        IrregularVerb[]verbos = gson.fromJson(json, IrregularVerb[].class);

        verbs = List.of(verbos);

        

    }

}
