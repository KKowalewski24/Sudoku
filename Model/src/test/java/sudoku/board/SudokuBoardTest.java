package sudoku.board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import sudoku.board.SudokuBoard;
import sudoku.solver.BacktrackingSudokuSolver;

public class SudokuBoardTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSecond;

    /*------------------------ METHODS REGION ------------------------*/
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
    public void isEditableFieldTest() {
        assertFalse(sudokuBoard.isEditableField(1, 1));
    }

    @Test
    public void setEditableFieldTest() {
        sudokuBoard.setEditableField(1, 1);
        assertTrue(sudokuBoard.isEditableField(1, 1));
    }

    @Test
    public void toStringTest() {
        assertNotNull(sudokuBoard.toString());
    }

    @Test
    public void convertSudokuBoardToStringTest() {
        assertNotNull(sudokuBoard.convertSudokuBoardToString());
    }

    @Test
    public void convertStringToSudokuBoardTest() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.SIZE * SudokuBoard.SIZE; i++) {
            builder.append(0);
        }

        assertNotNull(sudokuBoard.convertStringToSudokuBoard(builder.toString()));
    }

    @Test
    public void dualSideConvertTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();

        String values = sudokuBoard.convertSudokuBoardToString();
        sudokuBoard.convertStringToSudokuBoard(values);
        assertEquals(sudokuBoard, sudokuBoardSecond);

        String editable = sudokuBoard.convertIsEditableToString();
        sudokuBoard.convertStringToIsEditable(editable);
        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test
    public void convertIsEditableToStringTest() {
        sudokuBoard.setEditableField(3, 3);
        String temp = sudokuBoard.convertIsEditableToString();
        assertEquals(temp.charAt(3 * SudokuBoard.SIZE + 3), '1');
    }

    @Test
    public void convertStringToIsEditableTest() {
        StringBuilder builder = new StringBuilder();

        builder.append(1);
        for (int i = 0; i < SudokuBoard.SIZE * SudokuBoard.SIZE - 1; i++) {
            builder.append(0);
        }

        assertEquals(sudokuBoard.convertStringToIsEditable(builder.toString())
                .isEditableField(0, 0), true);
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
        assertEquals(sudokuBoard.hashCode(), sudokuBoardSecond.hashCode());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();

        assertTrue(sudokuBoard.equals(sudokuBoardSecond)
                && sudokuBoardSecond.equals(sudokuBoard));
    }
}
