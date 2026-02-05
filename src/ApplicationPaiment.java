import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Cette classe étend Application et permet de démarrer la page de paiment
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class ApplicationPaiment extends Application {
    /**
     * Cette fonction permet de démarrer la page de paiment
     * @param primaryStage la page de paiemnt
     */
    @Override
    public void start(Stage primaryStage) {
        FormulaireMediateur root = new FormulaireMediateur();
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paiment de la commande");
        primaryStage.show();
    }

    /**
     * Cette fonction permet de lancer le programme pour la page de paiment
     * @param args les arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}