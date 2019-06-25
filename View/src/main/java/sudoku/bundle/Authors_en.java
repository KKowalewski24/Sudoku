package sudoku.bundle;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"1. ", "Jan Karwowski ",},
                {"2. ", "Kamil Kowalewski "}
        };
    }
}
