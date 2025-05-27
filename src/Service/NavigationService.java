package Service;

import App.AppVerbos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface NavigationService {
    public  default void cambiarPantalla(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            AppVerbos.getStage().setScene(new Scene(root));
            AppVerbos.getStage().setTitle("Panel de usuarios");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al pasa de pantallas (AppVerbos)");
        }
    }

}
