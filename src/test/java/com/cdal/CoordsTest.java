package test.java.com.cdal;

import main.java.com.cdal.Coords;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordsTest {

    @Test
    public void testConstructorAndGetters() {
        Coords coords = new Coords(3, 4);
        assertEquals(3, coords.getL());
        assertEquals(4, coords.getC());
    }

    @Test
    public void testPlusMethodWithTwoCoords() {
        Coords c1 = new Coords(1, 2);
        Coords c2 = new Coords(3, 4);
        Coords result = c1.plus(c1, c2);
        assertEquals(4, result.getL());
        assertEquals(6, result.getC());
    }

    @Test
    public void testMoinsMethodWithTwoCoords() {
        Coords c1 = new Coords(5, 7);
        Coords c2 = new Coords(2, 3);
        Coords result = c1.moins(c1, c2);
        assertEquals(3, result.getL());
        assertEquals(4, result.getC());
    }

    @Test
    public void testPlusMethodWithCoordsAndInts() {
        Coords c1 = new Coords(2, 3);
        Coords result = c1.plus(c1, 3, 4);
        assertEquals(5, result.getL());
        assertEquals(7, result.getC());
    }

    @Test
    public void testMoinsMethodWithCoordsAndInts() {
        Coords c1 = new Coords(5, 7);
        Coords result = c1.moins(c1, 2, 3);
        assertEquals(3, result.getL());
        assertEquals(4, result.getC());
    }

    @Test
    public void testToString() {
        Coords coords = new Coords(6, 8);
        assertEquals("6;8", coords.toString());
    }
}
