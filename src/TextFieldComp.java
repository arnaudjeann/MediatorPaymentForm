import javafx.scene.control.TextField;

/**
 * Cette classe étend Composant et permet la création de TextField
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class TextFieldComp extends Composant {
    //Attribut
    private TextField control;

    //Constructeur
    public TextFieldComp(TextField control) {
        this.control = control;
    }

    /**
     * Cette fonction permet de détermine le texte à mettre dans un TextField
     * @param text le texte à mettre
     */
    public void setText(String text) {
        control.setText(text);
    }

    /**
     * Cette fonction permet de rendre visible ou non le TextField
     * @param visible le niveau de visibilité
     */
    public void setVisible(boolean visible) {
        control.setVisible(visible);
    }

    /**
     * Cette fonction permet de déterminer si un TextField est accessible ou non
     * @param disable le niveau d'accessibilité
     */
    public void setDisable(boolean disable) {
        control.setDisable(disable);
    }

    /**
     * Cette fonction permet de déclencher la notificiation lors d'un changement de valeur
     */
    @Override
    public void declencher() {
        mediateur.notifier(this, "VALUE_CHANGED");
    }
}