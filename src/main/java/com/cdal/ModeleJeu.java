package main.java.com.cdal;

public class ModeleJeu {
    public static final int LIGNES = 6;
    public static final int COLONNES = 7;
    public static final int PUISSANCE = 8;
    private Equipe[][] grille;
    private int selectIndex;
    private Equipe joueur;
    private Equipe gagnant;
    private boolean perdu;

    public ModeleJeu(Equipe joueur){
        init();
        this.joueur = joueur;
        this.gagnant = null;
        this.selectIndex = COLONNES/2;
    }

    public void init(){
        this.grille = new Equipe[LIGNES][COLONNES];
        for (int l = 0; l < LIGNES; l++) {
            this.grille[l] = new Equipe[COLONNES];
            for (int c = 0; c < COLONNES; c++) {
                this.grille[l][c] = Equipe.AUCUNE;
            }
        }
    }

    public void selectionGauche(){
        if(this.selectIndex > 0){
            this.selectIndex--;
        }
    }

    public void selectionDroite(){
        if(this.selectIndex < COLONNES-1){
            this.selectIndex++;
        }
    }

    public int casePlusBasse(int c){
        if(this.grille[0][c] != Equipe.AUCUNE) return -1;
        for (int l = 0; l < LIGNES; l++) {
            if(this.grille[l][c] != Equipe.AUCUNE){
                return l-1;
            }
        }
        return LIGNES-1;
    }

    public boolean drop() {
        int l = this.casePlusBasse(this.selectIndex);
        if(l!= -1){
            this.grille[l][this.selectIndex] = this.joueur;
            this.verifFin();
            this.selectIndex = COLONNES/2;
            this.joueur = this.joueur == Equipe.JAUNE ? Equipe.ROUGE : Equipe.JAUNE;
        }
        return l!=-1;
    }

    public void verifFin(){
        boolean remplie = true;
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                if(this.getCase(l, c) == Equipe.AUCUNE){
                    remplie = false;
                    continue;
                }
                Direction[] dirs = Direction.values();
                for (Direction dir : dirs) {
                    Coords co = dir.getVoisin(l, c);
                    if(co.getC() < 0 || co.getC() >= COLONNES || co.getL() < 0 || co.getL() >= LIGNES) continue;
                    verif(l, c, dir, 1);
                }
            }
        }
        this.perdu = remplie;
    }

    private void verif(int l, int c, Direction dir, int i){
        Coords co = dir.getVoisin(l, c);
        if(co.getC() < 0 || co.getC() >= COLONNES || co.getL() < 0 || co.getL() >= LIGNES) return;
        if(this.grille[co.getL()][co.getC()] == this.grille[l][c]){
            if(i == PUISSANCE-1){
                this.gagnant = this.grille[l][c];
                return;
            }
            verif(co.getL(), co.getC(), dir, i+1);
        }
    }

    public Equipe getCase(int l, int c){
        return this.grille[l][c];
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public Equipe getJoueur() {
        return joueur;
    }

    public void setJoueur(Equipe joueur) {
        System.out.println(this.joueur);
        this.joueur = joueur;
        System.out.println(this.joueur);
    }

    public boolean estGagnee(){
        return this.gagnant!= null;
    }

    public boolean estPerdu(){
        return this.perdu;
    }

    public Equipe getGagnant() {
        return gagnant;
    }

    public void reset(){
        init();
        this.joueur = gagnant;
        this.gagnant = null;
        this.selectIndex = COLONNES/2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < LIGNES; l++) {
            for (int c = 0; c < COLONNES; c++) {
                sb.append(this.grille[l][c]);
            }
            sb.append("\n");
        }
        sb.append("index: "+this.selectIndex);
        return sb.toString();
    }
}
