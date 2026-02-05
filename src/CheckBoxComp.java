import javafx.scene.control.CheckBox;
/**
 * Cette classe étend Composant et permet la création de CheckBox
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class CheckBoxComp extends Composant {
    //Attribut
    private CheckBox control;

    //Constructeur
    public CheckBoxComp(CheckBox control) {
        this.control = control;
    }

    /**
     * Cette fonction permet de savoir si le CheckBox est sélectionné ou non
     * @return true s'il est sélectionné, false sinon
     */
    public boolean isSelected() {
        return control.isSelected();
    }

    /**
     * Cette fonction permet de déclencher la notification lors de la synchronisation de l'adresse de livraison
     * et de facturation
     */
    @Override
    public void declencher() {
        mediateur.notifier(this, "ADR_SYNC");
    }
}