package sudoku;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @Before
    public void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    public void getSetMethodsTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 5);
        assertEquals(sudokuBoard.get(0, 0), 5);
    }

    @Test
    public void toStringTest() {
        StringBuilder tmpStr = new StringBuilder();
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            tmpStr.append("[0, 0, 0, 0, 0, 0, 0, 0, 0]\n");
        }

        assertTrue(sudokuBoard.toString().equals(tmpStr.toString()));
    }

    @Test
    public void equalsTest() {
        assertTrue(sudokuBoard.equals(sudokuBoard));
    }

    @Test
    public void hashCodeTest() {
        List<List<SudokuField>> board = Arrays.asList(new List[SudokuBoard.SIZE]);

        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            board.set(i, Arrays.asList(new SudokuField[SudokuBoard.SIZE]));
        }

        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                board.get(i).set(j, new SudokuField());
            }
        }

        assertTrue(sudokuBoard.hashCode() != board.hashCode());
    }

    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(1, 1));
    }

}
