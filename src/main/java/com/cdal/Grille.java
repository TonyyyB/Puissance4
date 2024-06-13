package main.java.com.cdal;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Grille extends StackPane{
    private GridPane grid;
    private List<Pion> selection;
    private List<List<Pion>> grille;
    private int selectIndex;
    private Equipe joueur;
    
    public Grille(){
        this.selectIndex = ModeleJeu.COLONNES/2;
        this.joueur = Equipe.JAUNE;
        initPions();
        grid.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(20), new Insets(Pion.SIZE*3,0,0,0))));
    }

    private void initPions(){
        if(this.grid == null){this.grid = new GridPane();this.getChildren().add(grid);}else{this.grid.getChildren().clear();}
        grid.setVgap(Pion.SIZE*0.8);
        grid.setHgap(Pion.SIZE*0.8);
        grid.setPadding(new Insets(10));
        grid.getChildren().clear();
        this.selection = new ArrayList<>();
        for (int c = 0; c < ModeleJeu.COLONNES; c++) {
            Pion p = new Pion();
            if(c==selectIndex) p.setEquipe(joueur);
            grid.add(p, c, 0);
            this.selection.add(p);
        }
        this.grille = new ArrayList<>();
        for (int l = 0; l < ModeleJeu.LIGNES; l++) {
            this.grille.add(new ArrayList<>());
            for (int c = 0; c < ModeleJeu.COLONNES; c++) {
                Pion p = new Pion();
                grid.add(p, c, l+1);
                this.grille.get(l).add(p);
            }
        }
    }

    private void initGrilleImage(){

    }

    public void maj(ModeleJeu modele, Change change){
        Pion changed = null;
        for (int l = 0; l < ModeleJeu.LIGNES; l++) {
            for (int c = 0; c < ModeleJeu.COLONNES; c++) {
                Pion pion = this.grille.get(l).get(c);
                if(pion.getEquipe() != modele.getCase(l, c)) changed = this.grille.get(l).get(c);
                pion.setEquipe(modele.getCase(l, c));
                pion.update();
            }
        }
        final Pion selected = this.selection.get(this.selectIndex);
        selected.setEquipe(Equipe.AUCUNE);
        if(change == Change.DROP && changed != null){
            final Pion toChange = changed;
            final Equipe dernierJoueur = this.joueur;
            selected.setEquipe(Equipe.AUCUNE);
            changed.setEquipe(Equipe.AUCUNE);

            double ty = -changed.sceneToLocal(selected.localToScene(changed.getTranslateX(), changed.getTranslateY())).getY();

            final Pion p = new Pion(this.joueur);
            TranslateTransition tt = new TranslateTransition();
            tt.setNode(p);
            tt.setToY(ty);
            tt.setDuration(Duration.millis(500));
            tt.setOnFinished((event) -> {
                grid.getChildren().remove(p);
                this.selection.get(this.selectIndex).setEquipe(this.joueur);
                toChange.setEquipe(dernierJoueur);
                AppliJeu.WAITING = false;
            });
            tt.play();
            grid.add(p, this.selectIndex, 0);
            p.setCenterY(ty);
            AppliJeu.WAITING = true;
        }
        this.selectIndex = modele.getSelectIndex();
        this.joueur = modele.getJoueur();
        if(changed == null) this.selection.get(this.selectIndex).setEquipe(this.joueur);
    }

    public void setJoueur(Equipe joueur) {
        this.joueur = joueur;
        this.selection.get(this.selectIndex).setEquipe(this.joueur);
        this.selection.get(this.selectIndex).update();
    }

    public void reset(){
        this.initPions();
    }
}
