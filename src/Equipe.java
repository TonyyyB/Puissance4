import javafx.scene.paint.Color;

public enum Equipe {
    AUCUNE(0, Color.WHITE, "V", " "),
    JAUNE(1, Color.GOLD, "J", "jaunes"),
    ROUGE(2, Color.RED, "R", "rouges");

    private int id;
    private Color color;
    private String symbole;
    private String nom;

    Equipe(int id, Color color, String symbole, String nom){
        this.id = id;
        this.color = color;
        this.symbole = symbole;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public String getSymbole() {
        return symbole;
    }

    public String getNom() {
        return nom;
    }

    public Equipe valueOf(int id){
        for(Equipe e : Equipe.values()){
            if(e.getId() == id){
                return e;
            }
        }
        return AUCUNE;
    }

    public Equipe valueOf(Color color){
        for(Equipe e : Equipe.values()){
            if(e.getColor() == color){
                return e;
            }
        }
        return AUCUNE;
    }

    @Override
    public String toString() {
        return this.getSymbole();
    }
}
