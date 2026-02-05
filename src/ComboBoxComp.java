import javafx.scene.control.ComboBox;

/**
 * Cette classe étend Composant et permet la création de ComboBox
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class ComboBoxComp extends Composant {
    //Attribut
    private ComboBox<String> control;

    //Constructeur
    public ComboBoxComp(ComboBox<String> control) {
        this.control = control;
    }

    /**
     * Cette fontion permet d'avoir la valeur d'un composant
     * @return la valeur
     */
    public String getValue() {
        return control.getValue();
    }

    /**
     * Cette fonction permet de déclencher la notification lors du changement de mode
     */
    @Override
    public void declencher() {
        mediateur.notifier(this, "MODE_CHANGE");
    }
}