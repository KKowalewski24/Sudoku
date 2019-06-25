package sudoku.dao;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import sudoku.dao.SudokuBoardDaoFactory;

public class SudokuBoardDaoFactoryTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void getFileDaoTest() {
        assertNotNull(factory.getFileDao("abc.txt"));
    }

    @Test
    public void getDatabaseDaoTest() {
        assertNotNull(factory.getDatabaseDao("abc.txt"));
    }
}