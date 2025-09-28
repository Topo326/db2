package com;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import com.mongodb.client.result.DeleteResult;

public class PatientDAO {

    private MongoCollection<Document> pacienteCollection;

    public PatientDAO(MongoDatabase database) {
        this.pacienteCollection = database.getCollection("Patient");
        System.out.println("PacienteDAO inicializado para la colección 'Pacientes'.");
    }


    public void insertPaciente(String nombre, String apellido) {
        Document paciente = new Document("nombre", nombre)
                .append("apellido", apellido)
                .append("activo", true);
        pacienteCollection.insertOne(paciente);
        System.out.println("-> Paciente insertado: " + nombre + " " + apellido);
    }

    public void findAllPacientes() {
        System.out.println("\n--- Ejecutando FIND (Listar todos los pacientes) ---");
        FindIterable<Document> pacientes = pacienteCollection.find();

        for (Document paciente : pacientes) {
            System.out.println("  - ID: " + paciente.getObjectId("_id") +
                    ", Nombre: " + paciente.getString("nombre") +
                    ", Apellido: " + paciente.getString("apellido"));
        }
    }

    public long deletePaciente(String nombre, String apellido) {
        Document filtro = new Document("nombre", nombre).append("apellido", apellido);
        DeleteResult result = pacienteCollection.deleteOne(filtro);
        return result.getDeletedCount();
    }
}
