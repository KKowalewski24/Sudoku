package sudoku.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import sudoku.board.SudokuBoard;
import sudoku.dao.Dao;
import sudoku.dao.SudokuBoardDaoFactory;
import sudoku.exception.DaoException;
import sudoku.utils.FxmlStageSetup;
import sudoku.utils.PopOutWindow;

public class ChoiceWindowControl {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxSystemLang;
    @FXML
    private ComboBox comboBoxSystemDifficult;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(ChoiceWindowControl.class.getName());

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser;

    private static SudokuBoard sudokuBoardFromSource;
    private static String level;
    private String language;

    /*------------------------ METHODS REGION ------------------------*/
    public static String getLevel() {
        return level;
    }

    public String getLanguage() {
        return language;
    }

    public static SudokuBoard getSudokuBoardFromSource() {
        return sudokuBoardFromSource;
    }

    @FXML
    private void initialize() throws IOException {
        comboBoxSystemLang.getItems().addAll(
                bundle.getString("_comboLang1"),
                bundle.getString("_comboLang2")
        );

        comboBoxSystemDifficult.getItems().addAll(
                bundle.getString("_lvlUltraEasy"),
                bundle.getString("_lvlEasy"),
                bundle.getString("_lvlMedium"),
                bundle.getString("_lvlHard")
        );
    }

    /**
     * onAction method to StartGame button.
     *
     * @param actionEvent - Action event object
     * @throws IOException required exception to throw
     */
    @FXML
    private void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        try {
            if (level == null) {
                ChoiceWindowControl.level = comboBoxSystemDifficult
                        .getSelectionModel().getSelectedItem().toString();
            }
            FxmlStageSetup.buildStage("/boardWindow.fxml", bundle);
            logger.info("Start game");
        } catch (NullPointerException e) {
            logger.warning("Bad game settings!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_startWindow"), Alert.AlertType.WARNING);
        }
    }

    /**
     * onAction method to ConfirmLang button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonConfirmLang(ActionEvent actionEvent) throws IOException {
        try {
            this.language = comboBoxSystemLang
                    .getSelectionModel().getSelectedItem().toString();

            if (language.equals(bundle.getString("_comboLang1"))) {
                Locale.setDefault(new Locale("en"));
            } else if (language.equals(bundle.getString("_comboLang2"))) {
                Locale.setDefault(new Locale("pl"));
            }

            FxmlStageSetup.buildStage("/choiceWindow.fxml",
                    bundle.getString("_windowTitle"), bundle);
            logger.info("Confirm language setttings!");
        } catch (NullPointerException e) {
            logger.warning("Bad language settings!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_langWindow"), Alert.AlertType.WARNING);
        }
    }

    /**
     * onAction method to Database button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonDatabase(ActionEvent actionEvent) {
        String filename;
        fileChooser = new FileChooser();

        try {
            filename = fileChooser.showOpenDialog(FxmlStageSetup.getStage()).getName();
            jdbcSudokuBoardDao = factory.getDatabaseDao(filename);
            sudokuBoardFromSource = jdbcSudokuBoardDao.read();
            ChoiceWindowControl.level = bundle.getString("_lvlFromExternal");
            comboBoxSystemDifficult.setDisable(true);
        } catch (NullPointerException | DaoException e) {
            logger.warning("Cannot read from database!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
        }
    }

    /**
     * onAction method to File button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonFile(ActionEvent actionEvent) {
        String filename;
        fileChooser = new FileChooser();

        try {
            filename = fileChooser.showOpenDialog(FxmlStageSetup.getStage()).getName();
            fileSudokuBoardDao = factory.getFileDao(filename);
            sudokuBoardFromSource = fileSudokuBoardDao.read();
            ChoiceWindowControl.level = bundle.getString("_lvlFromExternal");
            comboBoxSystemDifficult.setDisable(true);
        } catch (NullPointerException | DaoException e) {
            logger.warning("Cannot read from file!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
        }
    }

    /**
     * onAction method to Authors_en button.
     *
     * @param actionEvent - Action event object
     */
    @FXML
    private void onActionButtonAuthors(ActionEvent actionEvent) {
        ResourceBundle listBundle = ResourceBundle.getBundle("sudoku.Authors");
        popOutWindow.messageBox("",
                (listBundle.getObject("1. ") + "\n" + listBundle.getObject("2. ")),
                Alert.AlertType.INFORMATION);
    }
}
