package com.mitrian.server.database;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionManagerImpl implements DBConnectionManager {

    private BasicDataSource d =  new BasicDataSource();
    public  DBConnectionManagerImpl( String url, String user, String password){
        d.setUrl(url);
        d.setUsername(user);
        d.setPassword(password);
    }

    public Connection getConnection() throws SQLException {
        return d.getConnection();
    }

    public void close() throws SQLException {
        d.close();
    }
}
