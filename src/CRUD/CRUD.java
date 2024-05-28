package CRUD;

import java.sql.SQLException;

public interface CRUD<T> {
    int create() throws SQLException;

    T read() throws SQLException;

    int update() throws SQLException;

    int delete() throws SQLException;

    T inRead() throws SQLException;

    int inDelete() throws SQLException;
}