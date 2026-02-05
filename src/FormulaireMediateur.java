import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Cette classe étend VBox, implémente MediateurPaiment et permet de créer les composantes JavaFX nécessaire pour
 * le paiment ainsi que leur gestion
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public class FormulaireMediateur extends VBox implements MediateurPaiment {
    //Attributs UI originaux
    private ComboBox<String> modePaiment = new ComboBox<>();
    private TextField champNumCredit = new TextField();
    private TextField champDateExp = new TextField();
    private TextField champCVV = new TextField();
    private TextField champNumCadeau = new TextField();
    private TextField adrLivraison = new TextField();
    private TextField adrFacturation = new TextField();
    private CheckBox checkMemeAdresse =
            new CheckBox("L'adresse de facturation est la même que l'adresse de livraison");
    private ChoiceBox<String> optLivraison = new ChoiceBox<>();

    //Composantes du patron Médiateur
    private ComboBoxComp modePaimentComp;
    private TextFieldComp champNumCreditComp;
    private TextFieldComp champDateExpComp;
    private TextFieldComp champCVVComp;
    private TextFieldComp champNumCadeauComp;
    private TextFieldComp adrLivraisonComp;
    private TextFieldComp adrFacturationComp;
    private CheckBoxComp checkMemeAdresseComp;
    private ChoiceBoxComp optLivraisonComp;

    //Constructeur
    public FormulaireMediateur() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        setupUI();
        setupMediatorLinks();
        MAJChampsPaiment();
    }

    /**
     * Cette fonction permet de créer les textes dans la page ainsi que de disposer ces dernières et les comopsantes
     * de façon adéquate.
     */
    private void setupUI() {
        modePaiment.getItems().addAll("Carte de crédit", "Carte cadeau", "Paiment à la livraison");
        optLivraison.getItems().addAll("Livraison en main propre", "Se retrouver à l'extérieur",
                "Laisser à la porte");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Mode de paiment :"), 0, 0);
        grid.add(modePaiment, 1, 0);
        grid.add(new Label("    Numéro de carte :"), 0, 1);
        grid.add(champNumCredit, 1, 1);
        grid.add(new Label("    Date d'expiration de la carte :"), 0, 2);
        grid.add(champDateExp, 1, 2);
        grid.add(new Label("    Code de sécurité de la carte :"), 0, 3);
        grid.add(champCVV, 1, 3);

        grid.add(new Label("Numéro carte cadeau :"), 0, 4);
        grid.add(champNumCadeau, 1, 4);

        grid.add(new Label("Adresse de livraison :"), 0, 5);
        grid.add(adrLivraison, 1, 5);
        grid.add(checkMemeAdresse, 0, 6);

        grid.add(new Label("Adresse de facturation :"), 0, 7);
        grid.add(adrFacturation,1, 7);

        grid.add(new Label("Option de livraison :"), 0, 8);
        grid.add(optLivraison, 1, 8);
        optLivraison.setMinWidth(170);
        optLivraison.setMaxWidth(170);

        Label titre = new Label("Formulaire de paiment");
        titre.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-underline: true;");
        this.getChildren().addAll(titre, grid);
    }

    /**
     * Cette fonction permet de faire les liens avec le médiateur quand une action le nécessitant est réalisée
     */
    private void setupMediatorLinks() {
        modePaimentComp = new ComboBoxComp(modePaiment);
        modePaimentComp.setMediateur(this);
        modePaiment.setOnAction(evenement -> modePaimentComp.declencher());

        champNumCreditComp = new TextFieldComp(champNumCredit);
        champDateExpComp = new TextFieldComp(champDateExp);
        champCVVComp = new TextFieldComp(champCVV);
        champNumCadeauComp = new TextFieldComp(champNumCadeau);

        adrLivraisonComp = new TextFieldComp(adrLivraison);
        adrLivraisonComp.setMediateur(this);
        adrLivraison.textProperty().addListener((obs, old, val)
                -> adrLivraisonComp.declencher());

        adrFacturationComp = new TextFieldComp(adrFacturation);

        checkMemeAdresseComp = new CheckBoxComp(checkMemeAdresse);
        checkMemeAdresseComp.setMediateur(this);
        checkMemeAdresse.setOnAction(evenement -> checkMemeAdresseComp.declencher());

        optLivraisonComp = new ChoiceBoxComp(optLivraison);
        optLivraisonComp.setMediateur(this);
    }

    /**
     * Cette fonction permet de notifier lorsque le mode de paiment change ou lorsque la synchronisation de l'adresse
     * est activée
     * @param composant le composant en question
     * @param evenement l'évenement réalisé
     */
    @Override
    public void notifier(Object composant, String evenement) {
        if (composant == modePaimentComp) {
            MAJChampsPaiment();
            MAJOptionsLivraison();
        }

        if (composant == checkMemeAdresseComp || composant == adrLivraisonComp) {
            MAJChampsAdresse();
        }
    }

    /**
     * Cette fonction permet de mettre à jour le champ de paiment et les autres champs nécessaires
     * selon le mode de paiment choisi
     */
    private void MAJChampsPaiment() {
        String mode = modePaimentComp.getValue();

        boolean estCredit = "Carte de crédit".equals(mode);
        champNumCreditComp.setVisible(estCredit);
        champDateExpComp.setVisible(estCredit);
        champCVVComp.setVisible(estCredit);

        champNumCadeauComp.setVisible("Carte cadeau".equals(mode));

        if ("Paiment à la livraison".equals(mode)) {
            optLivraisonComp.removeItem("Laisser à la porte");
        } else {
            optLivraisonComp.addItem("Laisser à la porte");
        }
    }

    /**
     * Cette fonction permet de mettre à jour le champ d'adresse, soit de le rendre visible ou invisible, selon
     * les choix de paiment réalisés
     */
    private void MAJChampsAdresse() {
        if (checkMemeAdresseComp.isSelected()) {
            adrFacturationComp.setDisable(true);
            adrFacturationComp.setText(adrLivraison.getText());
        } else {
            adrFacturationComp.setDisable(false);
            adrFacturationComp.setText(null);
        }
    }

    /**
     * Cette fonction permet de mettre à jour les options de livraison selon le mode de paiment choisi
     */
    private void MAJOptionsLivraison() {
        if ("Paiment à la livraison".equals(modePaimentComp.getValue())) {
            if ("Laisser à la porte".equals(optLivraisonComp.getValue())) {
                optLivraisonComp.setValue(null);
            }
        }
    }
}