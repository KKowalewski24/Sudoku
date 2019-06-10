package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int SIZE = 9;
    public static final int NUMBER_OF_CELLS = SIZE * SIZE;

    private List<List<SudokuField>> board;

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * Constructor of the class.
     */
    public SudokuBoard() {
        //PRZYPISYWANIE DO REFERENCJI PIERSZEGO WYMIARU MACIERZY
        board = Arrays.asList(new List[SIZE]);

        //PRZYPISYWANIE DRUGIEGO WYMIARU MACIERZY
        for (int i = 0; i < SIZE; i++) {
            board.set(i, Arrays.asList(new SudokuField[SIZE]));
        }

        //ZMIANA ZAWARTOSCI LISTY Z NULL NA OBIEKTY SudokuField
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public int get(int i, int j) {
        return board.get(i).get(j).getFieldValue();
    }

    public void set(int i, int j, int value) {
        this.board.get(i).get(j).setFieldValue(value);
    }

    /**
     * Return a chosen row of matrix.
     *
     * @param row number of chosen row
     * @return SudokuRow object
     */
    public SudokuRow getRow(int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    /**
     * Return a chosen column of matrix.
     *
     * @param column number of chosen column
     * @return SudokuColumn object
     */
    public SudokuColumn getColumn(int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(i).get(column));
        }

        return new SudokuColumn(fields);
    }

    /**
     * Return a chosen small square of matrix.
     *
     * @param rowIndex    number of chosen row
     * @param columnIndex number of chosen column
     * @return SudokuBox object
     */
    public SudokuBox getBox(int rowIndex, int columnIndex) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.SIZE]);
        int index = 0;
        for (int i = 0; i < SudokuBox.BOX_SIZE; i++) {
            for (int j = 0; j < SudokuBox.BOX_SIZE; j++) {
                fields.set(index++, board.get(rowIndex * 3 + i).get(columnIndex * 3 + j));
            }
        }

        return new SudokuBox(fields);
    }

    public boolean isEditableField(int axisX, int axisY) {
        return board.get(axisX).get(axisY).isEmptyField();
    }

    public void setEditableField(int axisX, int axisY) {
        board.get(axisX).get(axisY).setEmptyField();
    }

    /**
     * Check if board is correct.
     *
     * @return boolean - if board is correct the true
     */
    public boolean checkBoard() {
        boolean isCorrect = true;
        //sprawdzanie wierszy
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i).get(j2).getFieldValue()) {
                        isCorrect = false;
                    }
                }
            }
        }

        //sprawdzanie kolumn
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board.get(i).get(j).getFieldValue()
                            == board.get(i2).get(j).getFieldValue()) {
                        isCorrect = false;
                    }
                }
            }
        }

        //sprawdzanie małych kwadratów
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //mały kwadrat (i, j)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1;
                         compared < 9; compared++) {
                        if (board.get(i * 3 + (checked / 3))
                                .get(j * 3 + (checked % 3)).getFieldValue()
                                == board.get(i * 3 + (compared / 3))
                                .get(j * 3 + (compared % 3)).getFieldValue()) {
                            isCorrect = false;
                        }
                    }
                }
            }
        }

        return isCorrect;
    }

    /**
     * Method convert SudokuBoard fields - values into String.
     *
     * @return string of SudokuBoard values.
     */
    public String convertSudokuBoardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                builder.append(String.valueOf(get(i, j)));
            }
        }

        return builder.toString();
    }

    /**
     * Method convert field isEditable into String.
     *
     * @return string of isEditable values
     */
    public String convertIsEditableToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                builder.append(String.valueOf(isEditableField(i, j) ? 1 : 0));
            }
        }

        return builder.toString();
    }

    /**
     * Method convert String with fields - values into SudokuBoard object.
     *
     * @param string SudokuBoard object fields - values in String representation
     * @return return SudokuBoard object
     */
    public SudokuBoard convertStringToSudokuBoard(String string) {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                set(i, j, Character.getNumericValue(string.charAt(i * SudokuBoard.SIZE + j)));
            }
        }

        return this;
    }

    /**
     * Method convert String with fields - isEditable to SudokuBoard object.
     *
     * @param string SudokuBoard object fields - isEditable in String representation
     * @return
     */
    public SudokuBoard convertStringToIsEditable(String string) {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                if (string.charAt(i * SudokuBoard.SIZE + j) == '1') {
                    setEditableField(i, j);
                }
            }
        }

        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        return new EqualsBuilder().append(board, ((SudokuBoard) obj).board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("board", board).toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }

        return sudokuBoard;
    }
}
