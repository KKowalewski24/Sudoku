package sudoku;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SudokuBoardDaoFactoryTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void getFileDaoTest() {
        assertNotNull(factory.getFileDao("abc"));
    }
}