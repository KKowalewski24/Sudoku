package sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSecond;

    @Before
    public void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    public void checkBoardFailTest() {
        assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    public void getSetMethodsTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 5);
        assertEquals(sudokuBoard.get(0, 0), 5);
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

    @Test
    public void toStringTest() {
        assertEquals(sudokuBoard.toString(), sudokuBoard.toString());
    }

    @Test
    public void equalsTest() {
        sudokuBoardSecond = new SudokuBoard();
        assertTrue(sudokuBoard.equals(sudokuBoardSecond)
                && sudokuBoardSecond.equals(sudokuBoard));
    }

    @Test
    public void hashCodeTest() {
        sudokuBoardSecond = new SudokuBoard();
        assertTrue(sudokuBoard.hashCode() == sudokuBoardSecond.hashCode());
    }
}
