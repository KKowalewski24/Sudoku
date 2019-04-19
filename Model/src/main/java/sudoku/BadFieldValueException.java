package sudoku;

public class BadFieldValueException extends IllegalArgumentException {
    public BadFieldValueException(final String message) {
        super(message);
    }
}
