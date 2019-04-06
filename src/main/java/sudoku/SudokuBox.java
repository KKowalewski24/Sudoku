package sudoku;

import java.util.List;

public class SudokuBox extends SudokuFieldGroup {
    public static final int BOX_SIZE = 3;

    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }
}
