package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuFieldGroup {

    /*------------------------ METHODS REGION ------------------------*/
    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuColumn(fields);
    }
}
