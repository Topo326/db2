package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/hospital?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String username = "root";
    private static final String password = "root_pass0110";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException classError) {
            classError.printStackTrace();
        }catch (SQLException sqlError){
            sqlError.printStackTrace();
        }
        return conn;
    }

    // Método para cerrar la conexión
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

