package sudoku;

import java.util.ResourceBundle;
import org.junit.Before;
import org.junit.Test;
import sudoku.board.SudokuBoard;
import sudoku.exception.EmptyBoardException;
import sudoku.exception.UnknownLevelException;

import static org.junit.Assert.assertEquals;
import sudoku.solver.BacktrackingSudokuSolver;

public class DifficultyLevelTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoard sudokuBoard;
    private DifficultyLevel difficultyLevel;
    private BacktrackingSudokuSolver solver;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        sudokuBoard = new SudokuBoard();
        difficultyLevel = new DifficultyLevel();
        solver = new BacktrackingSudokuSolver();
    }

    @Test(expected = UnknownLevelException.class)
    public void chooseLevelUnknownLevelExceptionTest() throws EmptyBoardException {
        difficultyLevel.chooseLevel(sudokuBoard, "wrongName");
    }

    @Test(expected = EmptyBoardException.class)
    public void chooseLevelEmptyBoardExceptionTest() throws EmptyBoardException {
        difficultyLevel.chooseLevel(sudokuBoard, bundle.getString("_lvlEasy"));
    }

    @Test
    public void chooseLevelTest1() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlUltraEasy"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[0]);
    }

    @Test
    public void chooseLevelTest2() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlEasy"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[1]);
    }

    @Test
    public void chooseLevelTest3() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlMedium"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[2]);
    }

    @Test
    public void chooseLevelTest4() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlHard"), DifficultyLevel.MULTIPLIER_LEVEL_ARRAY[3]);
    }

    @Test
    public void chooseLevelTest5() throws EmptyBoardException {
        checkEmptyFields(bundle.getString("_lvlFromExternal"), 0);
    }

    private void checkEmptyFields(String level, int multiplier) throws EmptyBoardException {
        solver.solve(sudokuBoard);
        SudokuBoard changedBoard = difficultyLevel.chooseLevel(sudokuBoard, level);
        int emptyFieldNumber = 0;

        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                if (changedBoard.get(i, j) == 0) {
                    emptyFieldNumber++;
                }
            }
        }

        assertEquals(emptyFieldNumber, DifficultyLevel.BASIC_LEVEL * multiplier);
    }
}