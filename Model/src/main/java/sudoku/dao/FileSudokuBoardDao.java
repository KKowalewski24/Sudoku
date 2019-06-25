package sudoku.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sudoku.board.SudokuBoard;
import sudoku.exception.FileOperationException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    /*------------------------ FIELDS REGION ------------------------*/
    private String filename;

    /*------------------------ METHODS REGION ------------------------*/
    FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws FileOperationException {
        SudokuBoard object = null;

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new FileOperationException(e);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }

        return object;
    }

    @Override
    public void write(SudokuBoard object) throws FileOperationException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }
}
