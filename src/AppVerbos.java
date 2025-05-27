
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppVerbos extends Application{

    // Continue aquí 
    // https://github.com/holamellamoyago/IrregularVerbs-Java.git

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader parent = new FXMLLoader(getClass().getResource("Resources/MainController.fxml"));
        Parent root = parent.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}