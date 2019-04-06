package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    public static final int SIZE = 9;
    public static final int NUMBER_OF_CELLS = SIZE * SIZE;

    private List<List<SudokuField>> board;

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

    public SudokuRow getRow(int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int column) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.SIZE]);
        for (int i = 0; i < SIZE; i++) {
            fields.set(i, board.get(i).get(column));
        }

        return new SudokuColumn(fields);
    }

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

    private boolean checkBoard() {
        //sprawdzanie wierszy
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board.get(i).get(j).getFieldValue() == board.get(i).get(j2).getFieldValue()) {
                        return false;
                    }
                }
            }
        }

        //sprawdzanie kolumn
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board.get(i).get(j).getFieldValue() == board.get(i2).get(j).getFieldValue()) {
                        return false;
                    }
                }
            }
        }

        //sprawdzanie małych kwadratów
        for (int I = 0; I < 3; I++) {
            for (int J = 0; J < 3; J++) {
                //mały kwadrat (I, J)
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board.get(I * 3 + (checked / 3)).get(J * 3 + (checked % 3)).getFieldValue() ==
                                board.get(I * 3 + (compared / 3)).get(J * 3 + (compared % 3)).getFieldValue()) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(final Object obj) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (((SudokuBoard) obj).get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            result.append(getRow(i).getFields());
            result.append("\n");
        }

        return result.toString();
    }
}
