package sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BacktrackingSudokuSolverTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        sudokuBoard = new SudokuBoard();
        solver = new BacktrackingSudokuSolver();
    }

    private boolean checkRows(SudokuBoard board) {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                for (int j2 = j + 1; j2 < SudokuBoard.SIZE; j2++) {
                    if (board.get(i, j) == board.get(i, j2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkColumns(SudokuBoard board) {
        for (int j = 0; j < SudokuBoard.SIZE; j++) {
            for (int i = 0; i < SudokuBoard.SIZE; i++) {
                for (int i2 = i + 1; i2 < SudokuBoard.SIZE; i2++) {
                    if (board.get(i, j) == board.get(i2, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkSmallSquares(SudokuBoard board) {
        for (int I = 0; I < 3; I++) {
            for (int J = 0; J < 3; J++) {
                //mały kwadrat (I, J)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board.get(I * 3 + (checked / 3), J * 3 + (checked % 3)) ==
                                board.get(I * 3 + (compared / 3), J * 3 + (compared % 3))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void solveTest() {
        solver.solve(sudokuBoard);

        assertTrue(checkRows(sudokuBoard));
        assertTrue(checkColumns(sudokuBoard));
        assertTrue(checkSmallSquares(sudokuBoard));
    }

    @Test
    public void solveRepeatTest() {
        SudokuBoard obj1 = new SudokuBoard();
        SudokuBoard obj2 = new SudokuBoard();
        solver.solve(obj1);
        solver.solve(obj2);

        assertTrue(!obj1.equals(obj2));
    }


}