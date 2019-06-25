package sudoku.controller;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import sudoku.controller.ChoiceWindowControl;

public class ChoiceWindowControlTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private ChoiceWindowControl choiceWindowControl = new ChoiceWindowControl();

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void contructorTest() {
        assertNotNull(choiceWindowControl);
    }

    @Test
    public void getLevelTest() {
        assertNull(ChoiceWindowControl.getLevel());
    }

    @Test
    public void getLanguageTest() {
        assertNull(choiceWindowControl.getLanguage());
    }

    @Test
    public void getSudokuBoardFromFileTest() {
        assertNull(ChoiceWindowControl.getSudokuBoardFromSource());
    }
}