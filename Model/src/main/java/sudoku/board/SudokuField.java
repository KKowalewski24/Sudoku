package sudoku.board;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sudoku.exception.BadFieldValueException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    /*------------------------ FIELDS REGION ------------------------*/
    private int value;
    private boolean isEmptyField;
    private ResourceBundle listBundle = ResourceBundle.getBundle("sudoku.bundle.Language");

    /*------------------------ METHODS REGION ------------------------*/
    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField() {
        isEmptyField = true;
    }

    /**
     * Method set value of the chosen field.
     *
     * @param value It it value that chosen field will be assigned
     */
    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new BadFieldValueException(listBundle.getObject("_wrongFieldValue").toString());
        }
        this.value = value;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(value, ((SudokuField) obj).value).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("value", value).toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField();
        sudokuField.value = this.value;
        return sudokuField;
    }
}
