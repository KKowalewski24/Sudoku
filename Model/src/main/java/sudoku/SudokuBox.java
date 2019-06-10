package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuFieldGroup {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int BOX_SIZE = 3;

    /*------------------------ METHODS REGION ------------------------*/
    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuBox(fields);
    }
}
