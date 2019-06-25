package sudoku.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import sudoku.board.SudokuField;
import sudoku.board.SudokuFieldGroup;
import sudoku.board.SudokuRow;
import sudoku.exception.BadGroupSizeException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SudokuFieldGroupTest {

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
    public void verifyValidTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();
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

    @Test
    public void getFieldsTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();

        List<Integer> sudokuRowList = sudokuRow.getFields();

        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < SudokuFieldGroup.SIZE; i++) {
            valueList.add(i + 1);
        }

        assertThat(sudokuRowList, is(valueList));
    }

    @Test
    public void toStringTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();
        assertNotNull(sudokuRow.toString());
    }

    @Test
    public void equalsTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();
        SudokuRow sudokuRowSecond = makeObjectWithValidList();

        assertTrue(sudokuRow.equals(sudokuRowSecond) && sudokuRowSecond.equals(sudokuRow));
    }

    @Test
    public void hashCodeTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();
        SudokuRow sudokuRowSecond = makeObjectWithValidList();

        assertEquals(sudokuRow.hashCode(), sudokuRowSecond.hashCode());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getSudokuFieldListTest() {
        SudokuRow sudokuRow = makeObjectWithValidList();
        SudokuField sudokuField = new SudokuField(3);
        List<SudokuField> fields = sudokuRow.getSudokuFieldList();
        fields.set(1, sudokuField);
    }
}