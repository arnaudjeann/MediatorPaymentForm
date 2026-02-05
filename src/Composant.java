/**
 * Cette classe abstraite permet de créer le médiateur pour le paiment et de déterminer le déclenchement
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public abstract class Composant {
    //Attribut
    protected MediateurPaiment mediateur;

    /**
     * Cette fonction permet d'attribuer le médiateur à l'attribut de la classe
     * @param mediateur le médiateur à attribuer
     */
    public void setMediateur(MediateurPaiment mediateur) {
        this.mediateur = mediateur;
    }

    /**
     * Cette fonction permet aux fonctions enfants de déclencher la notification
     */
    public abstract void declencher();
}