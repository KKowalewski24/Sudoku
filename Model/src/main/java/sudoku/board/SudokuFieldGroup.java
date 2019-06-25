package sudoku.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sudoku.exception.BadGroupSizeException;


public abstract class SudokuFieldGroup implements Cloneable, Serializable {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int SIZE = 9;
    private List<SudokuField> fields;
    private ResourceBundle listBundle = ResourceBundle.getBundle("sudoku.bundle.Language");

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * Constructor of the class.
     *
     * @param fields list of the SudokuField objects
     */
    public SudokuFieldGroup(final List<SudokuField> fields) {
        if (fields.size() != SIZE) {
            throw new BadGroupSizeException(listBundle.getObject("_wrongLength").toString());
        }
        this.fields = fields;
    }

    /**
     * Method verify correctness.
     *
     * @return boolean - if verify successful then true
     */
    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields.get(i).getFieldValue() == fields.get(i2).getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Method return unmodifiable list of SudokuField objects.
     *
     * @return unmodifiable list
     */
    public List<SudokuField> getSudokuFieldList() {
        return Collections.unmodifiableList(fields);
    }

    /**
     * Method return list of fields.
     *
     * @return List of Integers converted from list of SudokuField
     */
    public List<Integer> getFields() {
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            valueList.add(fields.get(i).getFieldValue());
        }

        return valueList;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(fields, ((SudokuFieldGroup) obj).fields).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fields", fields).toString();
    }

}
