package main.java.com.cdal;

public enum Direction{
    HAUT_GAUCHE(-1, -1),
    HAUT(-1, 0),
    HAUT_DROITE(-1, 1),
    GAUCHE(0, -1),
    DROITE(0, 1),
    BAS_GAUCHE(1, -1),
    BAS(1, 0),
    BAS_DROITE(1, 1);
    private int offsetL;
    private int offsetC;

    Direction(int offsetL, int offsetC){
        this.offsetL = offsetL;
        this.offsetC = offsetC;
    }

    public int getOffsetC() {
        return offsetC;
    }

    public int getOffsetL() {
        return offsetL;
    }

    public Coords getVoisin(int l, int c){
        return new Coords(getOffsetL()+l, getOffsetC()+c);
    }

    public static Coords[] voisins(int l, int c){
        Coords[] voisins = new Coords[values().length];
        for (int i = 0; i < values().length; i++) {
            voisins[i] = new Coords(values()[i].getOffsetL()+l, values()[i].getOffsetC()+c);
        }
        return voisins;
    }

    public static Coords[] voisins(){
        return Direction.voisins(0, 0);
    }

    @Override
    public String toString() {
        switch(this){
            case HAUT_GAUCHE:
                return "HG";
            case HAUT:
                return "H";
            case HAUT_DROITE:
                return "HD";
            case GAUCHE:
                return "G";
            case DROITE:
                return "D";
            case BAS_GAUCHE:
                return "BG";
            case BAS:
                return "B";
            case BAS_DROITE:
                return "BD";
            default:
                return "";
        }
    }
}