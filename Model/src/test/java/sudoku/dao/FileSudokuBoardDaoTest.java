package sudoku.dao;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import sudoku.board.SudokuBoard;
import sudoku.dao.Dao;
import sudoku.dao.SudokuBoardDaoFactory;
import sudoku.exception.DaoException;
import sudoku.exception.FileOperationException;

import static org.junit.Assert.assertEquals;

public class FileSudokuBoardDaoTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardSecond;
    private Dao<SudokuBoard> fileSudokuBoardDao;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void writeReadTest() throws DaoException {
        fileSudokuBoardDao = factory.getFileDao("xxx.txt");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test(expected = FileOperationException.class)
    public void readIOExceptionTest() throws DaoException {
        fileSudokuBoardDao = factory.getFileDao("yyy.txt");
        fileSudokuBoardDao.read();
    }

    @Test(expected = FileOperationException.class)
    public void writeIOExceptionTest() throws DaoException {
        if (SystemUtils.IS_OS_WINDOWS) {
            fileSudokuBoardDao = factory.getFileDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            fileSudokuBoardDao = factory.getFileDao("/");
        } else {
            fileSudokuBoardDao = factory.getFileDao("?");
        }
        fileSudokuBoardDao.write(sudokuBoard);
    }
}
