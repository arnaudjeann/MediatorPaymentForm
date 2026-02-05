import javafx.scene.control.ChoiceBox;
/**
 * Cette classe étend Composant et permet la création de ChoiceBox
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class ChoiceBoxComp extends Composant {
    //Attribut
    private ChoiceBox<String> control;

    //Constructeur
    public ChoiceBoxComp(ChoiceBox<String> control) {
        this.control = control;
    }

    /**
     * Cette fonction permet d'ajouter un item au composant s'il n'y est pas
     * @param item l'item à ajouter
     */
    public void addItem(String item) {
        if (!control.getItems().contains(item)) control.getItems().add(item);
    }

    /**
     * Cette fonction permet de retirer un item au composant
     * @param item l'item à retirer
     */
    public void removeItem(String item) {
        control.getItems().remove(item);
    }

    /**
     * Cette fonction permet de déterminer la valeur d'un composant
     * @param value la valeur en question
     */
    public void setValue(String value) {
        control.setValue(value);
    }

    /**
     * Cette fontion permet d'avoir la valeur d'un composant
     * @return la valeur
     */
    public String getValue() {
        return control.getValue();
    }

    /**
     * Cette fonction permet de déclencher la notification lors du changement d'option
     */
    @Override
    public void declencher() {
        mediateur.notifier(this, "OPTION_CHANGED");
    }
}