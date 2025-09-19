package com.service;

import com.util.DatabaseConnection;
import java.sql.SQLException;
import java.util.List;
public interface CallInterface<T> {
    List<T> callMethod(int parameter) throws SQLException;
}
