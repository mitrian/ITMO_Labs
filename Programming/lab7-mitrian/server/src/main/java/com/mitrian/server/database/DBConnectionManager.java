package com.mitrian.server.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionManager {
    Connection getConnection() throws SQLException;
    void close() throws SQLException;
}
