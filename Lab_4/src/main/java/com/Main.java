package com;

import com.mongodb.client.MongoDatabase;

public class Main {

    public static void main(String[] args) {

        MongoConnector connector = new MongoConnector();

        try {
            // Conexión y obtención de la DB
            connector.connect();
            MongoDatabase database = connector.getDatabase();

            // Inicialización del DAO
            PatientDAO pacienteDAO = new PatientDAO(database);

            // 1. Operación CREATE
            pacienteDAO.insertPaciente("Roberto", "Sánchez");
            pacienteDAO.insertPaciente("Elena", "Castro");

            // 2. Operación READ
            pacienteDAO.findAllPacientes();

            // 4. Operación DELETE
            long deleted = pacienteDAO.deletePaciente("Elena", "Castro");
            System.out.println("-> Pacientes eliminados: " + deleted);

            // Re-listar para ver el cambio
            pacienteDAO.findAllPacientes();

            // Manejo de Excepciones
        } catch (RuntimeException e) {
            System.err.println("\n--- ❌ ERROR FATAL EN LA APLICACIÓN ---");
            System.err.println("Mensaje de error: " + e.getMessage());
        } finally {
            // Asegurar el cierre de la conexión al finalizar
            connector.close();
        }
    }
}
