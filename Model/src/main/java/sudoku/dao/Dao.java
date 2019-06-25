package sudoku.dao;

import sudoku.exception.DaoException;

public interface Dao<T> {
    T read() throws DaoException;

    void write(T object) throws DaoException;
}
