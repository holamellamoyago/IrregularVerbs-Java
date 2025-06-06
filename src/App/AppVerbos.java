package App;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppVerbos extends Application{

    // Continue aquí 
    // https://github.com/holamellamoyago/IrregularVerbs-Java.git
    private static Stage stage ; 


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader parent = new FXMLLoader(getClass().getResource("../Resources/MenuPrincipalScreen.fxml"));
        Parent root = parent.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Learn irregular verbs");
        primaryStage.setResizable(false);
        primaryStage.show();
        // Conexion.conectar();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppVerbos.stage = stage;
    }


    
}