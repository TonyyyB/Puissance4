package main.java.com.cdal;
public class Coords{
    private int l;
    private int c;
    public Coords(int l, int c){
        this.l = l;
        this.c = c;
    }
    public int getL() {return l;}
    public int getC() {return c;}
    public Coords plus(Coords c1, Coords c2){return new Coords(c1.getL()+c2.getL(), c1.getC()+c2.getC());}
    public Coords moins(Coords c1, Coords c2){return new Coords(c1.getL()-c2.getL(), c1.getC()-c2.getC());}
    public Coords plus(Coords c1, int l, int c){return new Coords(c1.getL()+l, c1.getC()+c);}
    public Coords moins(Coords c1, int l, int c){return new Coords(c1.getL()-l, c1.getC()-c);}
    @Override
    public String toString() {
        return l+";"+c;
    }
}