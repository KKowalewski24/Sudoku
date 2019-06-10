package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sudoku.exception.DatabaseException;

/*NICE TUTORIAL FOR JDBC http://www.sqlitetutorial.net/sqlite-java/ */

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String DB_NAME = "SudokuBoard";
    private String filename;

    /*------------------------ METHODS REGION ------------------------*/
    JdbcSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    private Connection prepareConnection(String jdbcUrl) throws DatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return connection;
    }

    @Override
    public SudokuBoard read() throws DatabaseException {
        SudokuBoard sudokuBoard = new SudokuBoard();
        String jdbcUrl = "jdbc:sqlite:" + filename;
        Connection connection = prepareConnection(jdbcUrl);
        String receivedData;
        ResultSet resultSet;
        String selectData = "select tableName, fields, isEditable from "
                + DB_NAME + " where tableName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, filename);
            resultSet = preparedStatement.executeQuery();
            receivedData = resultSet.getString(2);
            sudokuBoard.convertStringToSudokuBoard(receivedData);
            receivedData = resultSet.getString(3);
            sudokuBoard.convertStringToIsEditable(receivedData);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws DatabaseException {
        String jdbcUrl = "jdbc:sqlite:./" + filename;
        Connection connection = prepareConnection(jdbcUrl);

        String createTable = "create table " + DB_NAME
                + "(tableName varchar(20) primary key not null, "
                + "fields varchar(81),isEditable varchar(81))";

        String insertData = "insert into SudokuBoard(tableName,fields,isEditable) values (?,?,?)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setString(1, filename);
            preparedStatement.setString(2, sudokuBoard.convertSudokuBoardToString());
            preparedStatement.setString(3, sudokuBoard.convertIsEditableToString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
