package test.java.com.cdal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.cdal.*;

public class PionTest {
    
    private Pion pion;

    @BeforeEach
    public void setUp(){
        pion = new Pion();
        pion.setEquipe(Equipe.JAUNE);
    }

    @Test
    public void testGetEquipe(){
        assertEquals(Equipe.JAUNE, pion.getEquipe());
    }

    @Test
    public void testSetEquipe(){
        pion.setEquipe(Equipe.ROUGE);
        assertEquals(Equipe.ROUGE, pion.getEquipe());
    }

    @Test
    public void testEstVide(){
        assertEquals(false, pion.estVide());
    }

    @Test
    public void testReset(){
        pion.reset();
        assertEquals(Equipe.AUCUNE, pion.getEquipe());
    }
}
