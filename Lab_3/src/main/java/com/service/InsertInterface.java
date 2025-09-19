package com.service;
import com.util.DatabaseConnection;
import java.sql.*;

public interface InsertInterface {
    DatabaseConnection dbconn = new DatabaseConnection();
    public void insertMethod() throws SQLException;
}
