package ru.itis.rasimusv.repositories;

import java.sql.*;

public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
