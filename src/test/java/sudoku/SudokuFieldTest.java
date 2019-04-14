package sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuFieldTest {
    private SudokuField sudokuField;
    private SudokuField sudokuFieldSecond;

    @Before
    public void setUp() {
        sudokuField = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(sudokuField.getFieldValue(), 0);
    }

    @Test
    public void setFieldValueTest() {
        sudokuField.setFieldValue(5);
        assertEquals(sudokuField.getFieldValue(), 5);
    }

    @Test(expected = BadFieldValueException.class)
    public void setBadFieldValueTest() {
        sudokuField.setFieldValue(-1);
    }

    @Test(expected = BadFieldValueException.class)
    public void setBadFieldValueTestSecond() {
        sudokuField.setFieldValue(10);
    }

    @Test
    public void toStringTest() {
        assertNotNull(sudokuField.toString());
    }

    @Test
    public void equalsTest() {
        sudokuFieldSecond = new SudokuField();
        assertTrue(sudokuField.equals(sudokuFieldSecond)
                && sudokuFieldSecond.equals(sudokuField));
    }

    @Test
    public void hashCodeTest() {
        sudokuFieldSecond = new SudokuField();
        assertEquals(sudokuField.hashCode(), sudokuFieldSecond.hashCode());
    }

}