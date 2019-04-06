package sudoku;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuFieldGroupTest {

    @Test
    public void verifyValidTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertFalse(sudokuRow.verify());
    }

    @Test(expected = BadGroupSizeException.class)
    public void badGroupSizeTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }
}