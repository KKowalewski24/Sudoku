package sudoku;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SudokuRowTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuRow sudokuRow;
    private SudokuRow sudokuRowSecond;

    /*------------------------ METHODS REGION ------------------------*/
    private SudokuRow makeObjectWithValidList() {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }

    @Test
    public void CloneTest() throws CloneNotSupportedException {
        sudokuRow = makeObjectWithValidList();
        sudokuRowSecond = (SudokuRow) sudokuRow.clone();

        assertTrue(sudokuRow.equals(sudokuRowSecond)
                && sudokuRowSecond.equals(sudokuRow));
    }
}