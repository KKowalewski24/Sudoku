package sudoku;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SudokuBoxTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBox sudokuBox;
    private SudokuBox sudokuBoxSecond;

    /*------------------------ METHODS REGION ------------------------*/
    private SudokuBox makeObjectWithValidList() {
        return new SudokuBox(Arrays.asList(
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
        sudokuBox = makeObjectWithValidList();
        sudokuBoxSecond = (SudokuBox) sudokuBox.clone();

        assertTrue(sudokuBox.equals(sudokuBoxSecond)
                && sudokuBoxSecond.equals(sudokuBox));
    }
}