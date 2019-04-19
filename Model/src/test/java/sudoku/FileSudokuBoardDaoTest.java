package sudoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;

    @Test
    public void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("xxx");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test(expected = RuntimeException.class)
    public void readIOExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("yyy");
        fileSudokuBoardDao.read();
    }

    @Test(expected = RuntimeException.class)
    public void writeIOExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("?");
        fileSudokuBoardDao.write(sudokuBoard);
    }

}
