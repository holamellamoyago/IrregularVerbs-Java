package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    @FXML
    private Label txtHelpSimple;

    @FXML
    private Label txtHelpTranslate;

    @FXML
    private Label txtHelpParticiple;

    private List<IrregularVerb> verbs;
    private IrregularVerb irVrb;
    private Set<IrregularVerb> verbosMostrados = new TreeSet<>();

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

        if (verificarCampo(txtFieldSimple.getText(), irVrb.getSimple())) {
            txtFieldSimple.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldSimple.setStyle("-fx-background-color:red;");
            message += "simple ";
            txtHelpSimple.setText(mostrarAyuda(irVrb.getSimple()));

        }

        if (verificarCampo(txtFieldParticiiple.getText(), irVrb.getParticiple())) {
            txtFieldParticiiple.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldParticiiple.setStyle("-fx-background-color:red;");
            message += "participle ";
            txtHelpParticiple.setText(mostrarAyuda(irVrb.getParticiple()));
            // txtHelpParticiple.setText(mostrarAyuda(txtFieldParticiiple.getText()));
        }

        if (verificarTraduccion(txtFieldTraduccion.getText(), irVrb.getTranslation())) {
            txtFieldTraduccion.setStyle("-fx-background-color:green;");
            contador++;
        } else {
            txtFieldTraduccion.setStyle("-fx-background-color:red;");
            message += "traducción ";
            txtHelpTranslate.setText(mostrarAyuda(irVrb.getTranslation()));
            // txtHelpTranslate.setText(mostrarAyuda(txtFieldParticiiple.getText()));
        }

        if (contador < 3) {
            txtMessage.setText(message);
        } else {
            generarNuevaPartida();
        }

    }

    private void generarNuevaPartida() {
        verbosMostrados.add(irVrb);
        txtMessage.setText("Enhorabuena , +1 punto");

        txtFieldSimple.setStyle("-fx-background-color:white;");
        txtFieldParticiiple.setStyle("-fx-background-color:white;");
        txtFieldTraduccion.setStyle("-fx-background-color:white;");

        txtFieldSimple.setText("");
        txtFieldParticiiple.setText("");
        txtFieldTraduccion.setText("");

        txtHelpSimple.setText("");
        txtHelpParticiple.setText("");
        txtHelpTranslate.setText("");
        

        do {
            irVrb = IrregularVerb.getVb(verbs);
            lblText.setText(irVrb.getInfinitive());
        } while (verbosMostrados.contains(irVrb));

        System.out.println(verbosMostrados);
    }

    private boolean verificarCampo(String escrito, String correcto) {
        // Las paso a minúsculas
        escrito = escrito.toLowerCase();
        correcto = correcto.toLowerCase();

        if (escrito.equals(correcto))
            return true;
        else
            return false;
    }

    private boolean verificarTraduccion(String escrito, String correcto) {
        boolean iguales = false;
        int contador = 0;

        // Las paso a minúsculas
        escrito = escrito.toLowerCase();
        correcto = correcto.toLowerCase();

        // Hago un array de cada una para después comparar,
        // porque hay diferentes soluciones a la correcta
        String[] arrayCorrecto = correcto.split(" ");
        String[] arrayEscrito = escrito.split(" ");

        // Antes de una solución mas díficil si esto es equals que ya devuelva
        if (escrito.equals(correcto))
            return true;

        // Voy a hacer que si detecta ya una palabra (Puede ser una frase) que ya la de
        // por válida
        for (String e : arrayEscrito) {
            for (String c : arrayCorrecto) {
                if (e.equals(c))
                    iguales = true;
            }
        }
        return iguales;
    }

    private String mostrarAyuda(String verbo) {

        char[] letras = new char[verbo.length()];

        for (int i = 0; i < letras.length; i++) {
            letras[i] = '-';
        }

        switch (verbo.length()) {
            case 1, 2, 3, 4: {
                letras[0] = verbo.charAt(0);
                letras[letras.length - 1] = verbo.charAt(verbo.length() - 1);
                break;
            }
            default: {
                letras[0] = verbo.charAt(0);
                letras[letras.length - 1] = verbo.charAt(verbo.length() - 1);
                letras[(letras.length - 1) / 2] = verbo.charAt((verbo.length() - 1) / 2);
                break;
            }
        }

        verbo = "";
        for (char c : letras) {
            verbo += c;
        }

        return verbo;
    }

    @FXML
    public void importarVerbos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        StringBuilder fileContent = new StringBuilder();
        ExtensionFilter filter = new ExtensionFilter("Debes importarlo en formato JSON", "*.json");
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
        IrregularVerb[] verbos = gson.fromJson(json, IrregularVerb[].class);

        verbs = List.of(verbos);

    }

}
