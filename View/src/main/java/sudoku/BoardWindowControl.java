package sudoku;

import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import sudoku.exception.DaoException;
import sudoku.exception.EmptyBoardException;

public class BoardWindowControl {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private DifficultyLevel difficultyLevel = new DifficultyLevel();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(BoardWindowControl.class.getName());

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser;
    private File file;

    /*------------------------ METHODS REGION ------------------------*/
    @FXML
    private void initialize() throws EmptyBoardException {
        if (ChoiceWindowControl.getSudokuBoardFromSource() != null) {
            sudokuBoard = ChoiceWindowControl.getSudokuBoardFromSource();
        } else {
            solver.solve(sudokuBoard);
            difficultyLevel.chooseLevel(sudokuBoard, ChoiceWindowControl.getLevel());
        }

        fillGrid();
    }

    /**
     * Method fill GripPane in .fxml file.
     */
    private void fillGrid() {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));

                if (!(sudokuBoard.get(i, j) == 0 || sudokuBoard.isEditableField(i, j))) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                } else if (sudokuBoard.get(i, j) != 0 && sudokuBoard.isEditableField(i, j)) {
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }

                sudokuBoardGrid.add(textField, j, i);
            }
        }
    }

    /**
     * Method check if TextFields contain permitted number.
     *
     * @return boolean if TextField is valid true if not false
     */
    private boolean isInputValid() {
        boolean isValid = true;

        for (int i = 0; i < SudokuBoard.SIZE * SudokuBoard.SIZE; i++) {
            String fieldValue = ((TextField) sudokuBoardGrid.getChildren().get(i)).getText();
            if (!((fieldValue.matches("[1-9]")) || (fieldValue.equals("")))) {
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Method prepare sudokuBoard to save in file or datebase.
     */
    private void updateBoard() {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int j = 0; j < SudokuBoard.SIZE; j++) {
                String fieldValue = ((TextField) sudokuBoardGrid
                        .getChildren().get(i * SudokuBoard.SIZE + j)).getText();
                if (!fieldValue.equals("")) {
                    sudokuBoard.set(i, j, Integer.parseInt(fieldValue));
                } else {
                    sudokuBoard.set(i, j, 0);
                }
            }
        }
    }

    /**
     * onAction method to Check button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonCheck(ActionEvent actionEvent) {
        if (!isInputValid()) {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        } else {
            updateBoard();
            if (sudokuBoard.checkBoard()) {
                popOutWindow.messageBox("",
                        bundle.getString("_wonWindow"), Alert.AlertType.INFORMATION);
            } else {
                popOutWindow.messageBox("",
                        bundle.getString("_lostWindow"), Alert.AlertType.INFORMATION);
            }
        }
    }

    /**
     * onAction method to Database button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonDatabase(ActionEvent actionEvent) {
        fileChooser = new FileChooser();

        if (isInputValid()) {
            updateBoard();
            try {
                file = fileChooser.showSaveDialog(FxmlStageSetup.getStage());
                jdbcSudokuBoardDao = factory.getDatabaseDao(file.getName());
                jdbcSudokuBoardDao.write(sudokuBoard);
            } catch (NullPointerException | DaoException e) {
                logger.warning("Cannot save to database!");
                popOutWindow.messageBox(bundle.getString("_warning"),
                        bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
            }
        } else {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        }
    }

    /**
     * onAction method to File button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonFile(ActionEvent actionEvent) {
        fileChooser = new FileChooser();

        if (isInputValid()) {
            updateBoard();
            try {
                file = fileChooser.showSaveDialog(FxmlStageSetup.getStage());
                fileSudokuBoardDao = factory.getFileDao(file.getName());
                fileSudokuBoardDao.write(sudokuBoard);
            } catch (NullPointerException | DaoException e) {
                logger.warning("Cannot save to file!");
                popOutWindow.messageBox(bundle.getString("_warning"),
                        bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
            }
        } else {
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_validWindow"), Alert.AlertType.WARNING);
        }
    }
}
