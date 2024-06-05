import javafx.scene.shape.Circle;

public class Pion extends Circle{
    public static final double SIZE = 20;
    private Equipe equipe;
    public Pion(){
        this.equipe = Equipe.AUCUNE;
        init();
    }

    public Pion(Equipe equipe){
        this.equipe = equipe;
        init();
    }

    private void init(){
        this.setRadius(SIZE);
        update();
    }

    public void update(){
        this.setFill(equipe.getColor());
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
        update();
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public boolean estVide(){
        return this.equipe == Equipe.AUCUNE;
    }

    public void reset(){
        this.equipe = Equipe.AUCUNE;
        update();
    }
}
