package com.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

// Esto es una prueba de conexion que encontre en satckoverflow
public class TestConnetion {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
            Connection conn = dbConnection.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("Conexión exitosa a: " + metaData.getDatabaseProductName());
            System.out.println("Versión: " + metaData.getDatabaseProductVersion());
            System.out.println("Conectado a la base de datos: " + conn.getCatalog());
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

}

