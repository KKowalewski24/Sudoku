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
    public void writeReadTest() throws IOException {
        fileSudokuBoardDao = factory.getFileDao("xxx.txt");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);

        Files.deleteIfExists(Paths.get("xxx.txt"));
    }

    @Test(expected = FileOperationException.class)
    public void readIOExceptionTest() throws IOException {
        Files.deleteIfExists(Paths.get("xxx.txt"));
        fileSudokuBoardDao = factory.getFileDao("xxx.txt");
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
