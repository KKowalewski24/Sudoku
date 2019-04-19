package sudoku;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField implements Serializable {
    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    /**
     * Method set value of the chosen field.
     *
     * @param value It it value that chosen field will be assigned
     */
    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new BadFieldValueException("Must be <1,9>");
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
}
