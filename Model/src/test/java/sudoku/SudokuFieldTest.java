package sudoku;

import org.junit.Before;
import org.junit.Test;
import sudoku.exception.BadFieldValueException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SudokuFieldTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuField sudokuField;
    private SudokuField sudokuFieldSecond;

    /*------------------------ METHODS REGION ------------------------*/
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
    public void isEmptyFieldTest() {
        assertFalse(sudokuField.isEmptyField());
    }

    @Test
    public void setEmptyFieldTest() {
        sudokuField.setEmptyField();
        assertTrue(sudokuField.isEmptyField());
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

    @Test
    public void compareToTest() {
        sudokuFieldSecond = new SudokuField();

        sudokuField.setFieldValue(4);
        sudokuFieldSecond.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), 0);

        sudokuField.setFieldValue(8);
        sudokuFieldSecond.setFieldValue(4);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), 1);

        sudokuField.setFieldValue(4);
        sudokuFieldSecond.setFieldValue(8);
        assertEquals(sudokuField.compareTo(sudokuFieldSecond), -1);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        sudokuField = new SudokuField(8);
        sudokuFieldSecond = (SudokuField) sudokuField.clone();

        assertTrue(sudokuField.equals(sudokuFieldSecond)
                && sudokuFieldSecond.equals(sudokuField));
    }
}