package sudoku;

public class SudokuBoardDaoFactory {

    /*------------------------ METHODS REGION ------------------------*/
    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
