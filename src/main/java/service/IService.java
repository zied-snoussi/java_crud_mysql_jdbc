package service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void insert(T t) throws SQLException;
    void delete(T t) throws SQLException;
    void update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
    T readById(int id) throws SQLException;
}