package sudoku.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import sudoku.utils.FieldCoordinates;

public class FieldCoordinatesTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private FieldCoordinates fieldCoordinates;
    private FieldCoordinates fieldCoordinatesSecond;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        fieldCoordinates = new FieldCoordinates(2, 8);
    }

    @Test
    public void getAxisXTest() {
        assertEquals(fieldCoordinates.getAxisX(), 2);
    }

    @Test
    public void getAxisYTest() {
        assertEquals(fieldCoordinates.getAxisY(), 8);
    }

    @Test
    public void toStringTest() {
        assertNotNull(fieldCoordinates.toString());
    }

    @Test
    public void equalsTest() {
        fieldCoordinatesSecond = new FieldCoordinates(2, 8);
        Integer integer = new Integer(5);

        assertTrue(fieldCoordinates.equals(fieldCoordinates));
        assertFalse(fieldCoordinates.equals(null));
        assertFalse(fieldCoordinates.equals(integer));
        assertTrue(fieldCoordinates.equals(fieldCoordinatesSecond)
                && fieldCoordinatesSecond.equals(fieldCoordinates));
    }

    @Test
    public void hashCodeTest() {
        fieldCoordinatesSecond = new FieldCoordinates(2, 8);
        assertEquals(fieldCoordinates.hashCode(), fieldCoordinatesSecond.hashCode());
    }
}