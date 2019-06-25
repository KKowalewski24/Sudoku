package sudoku.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import sudoku.board.SudokuBoard;
import sudoku.dao.Dao;
import sudoku.dao.SudokuBoardDaoFactory;
import sudoku.exception.DaoException;
import sudoku.exception.DatabaseException;

import static org.junit.Assert.assertEquals;

public class JdbcSudokuBoardDaoTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardSecond;
    private Dao<SudokuBoard> databaseSudokuBoardDao;

    /*------------------------ METHODS REGION ------------------------*/

    @Test
    public void writeReadDbTest() throws IOException {
        Files.deleteIfExists(Paths.get("./xxx.db"));

        databaseSudokuBoardDao = factory.getDatabaseDao("xxx.db");
        databaseSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = databaseSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test(expected = DatabaseException.class)
    public void name() throws DaoException {
        if (SystemUtils.IS_OS_WINDOWS) {
            databaseSudokuBoardDao = factory.getDatabaseDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            databaseSudokuBoardDao = factory.getDatabaseDao("/");
        } else {
            databaseSudokuBoardDao = factory.getDatabaseDao("?");
        }
        databaseSudokuBoardDao.write(sudokuBoard);
    }
}