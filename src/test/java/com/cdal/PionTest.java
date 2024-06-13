package test.java.com.cdal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

import org.junigt.jupier.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PionTest {
    
    private Pion pion;
    private Equipe equipe;

    @BeforeEach
    public void setUp(){
        pion = new Pion();
        pion.setEquipe(equipe.JAUNE);
    }

    @Test
    public void testGetEquipe(){
        assertEquals(equipe.JAUNE, pion.getEquipe());
    }

    @Test
    public void testSetEquipe(){
        pion.setEquipe(equipe.ROUGE);
        assertEquals(equipe.ROUGE, pion.getEquipe());
    }

    @Test
    public void testEstVide(){
        assertEquals(false, pion.estVide());
    }

    @Test
    public void testReset(){
        pion.reset();
        assertEquals(equipe.AUCUNE, pion.getEquipe());
    }
}
