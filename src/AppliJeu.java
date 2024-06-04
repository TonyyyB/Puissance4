import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * AppliJeu
 */
public class AppliJeu extends Application{
    private ModeleJeu modele;
    private Grille grille;
    private Label tourL;
    private Label scoreJ;
    private Label scoreR;
    public static boolean WAITING;
    @Override
    public void init() throws Exception {
        this.modele = new ModeleJeu(Equipe.JAUNE);
        WAITING = false;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane top = new StackPane();
        top.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Label titre = new Label("Puissance 4");
        titre.setFont(new Font(40));
        top.getChildren().add(titre);
        root.setTop(top);
        root.setCenter(fenetreJeu());
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new ControleurClavier(this, this.modele));
        stage.setScene(scene);
        stage.show();
    }

    public Pane fenetreJeu(){
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        this.tourL = new Label("Au tour des "+this.modele.getJoueur().getNom());
        this.tourL.setFont(new Font(20));
        bp.setTop(tourL);

        Label s = new Label("Score");
        s.setFont(new Font(20));
        
        this.scoreJ = new Label("0");
        this.scoreR = new Label("0");
        this.scoreJ.setFont(new Font(20));
        this.scoreR.setFont(new Font(20));
        this.scoreJ.setTextFill(Equipe.JAUNE.getColor());
        this.scoreR.setTextFill(Equipe.ROUGE.getColor());
        
        VBox vb = new VBox();
        vb.getChildren().addAll(s, this.scoreJ, this.scoreR);
        bp.setRight(vb);
        
        this.grille = new Grille();
        bp.setCenter(this.grille);
        return bp;
    }

    public void maj(Change change){
        this.grille.maj(this.modele, change);
        this.tourL.setText("Au tour des " + this.modele.getJoueur().getNom());
        if(this.modele.estGagnee()){
            Alert popup = popUpGagnee(this.modele.getGagnant());
            popup.setOnCloseRequest((e) -> {
                this.reset();
                this.grille.maj(this.modele, change);
                this.tourL.setText("Au tour des " + this.modele.getJoueur().getNom());
            });
            if(this.modele.getGagnant() == Equipe.JAUNE){
                this.scoreJ.setText(Integer.toString(Integer.parseInt(this.scoreJ.getText())+1));
            }else if(this.modele.getGagnant() == Equipe.ROUGE){
                this.scoreR.setText(Integer.toString(Integer.parseInt(this.scoreR.getText())+1));
            }
            popup.showAndWait();
        }
    }

    public Alert popUpGagnee(Equipe gagnant){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Les "+gagnant.getNom()+" ont gagnés !");
        alert.setHeaderText("L'équipe des "+gagnant.getNom()+" a gagné !");
        return alert;
    }

    public void reset(){
        this.modele.reset();
        this.grille.reset();
    }

    public Grille getGrille() {
        return grille;
    }

    public static void main(String[] args) {
        launch(AppliJeu.class);
    }
}