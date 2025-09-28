package com;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;

public class MongoConnector {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "Hospital";

    private MongoClient mongoClient;

    public MongoClient connect() {
        try {
            ConnectionString connString = new ConnectionString(CONNECTION_STRING);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();

            mongoClient = MongoClients.create(settings);
            System.out.println("Conexión a MongoDB establecida.");
            return mongoClient;

        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB.");
            throw new RuntimeException("Error de conexión: " + e.getMessage(), e);
        }
    }

    public MongoDatabase getDatabase() {
        if (mongoClient == null) {
            connect();
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }


    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
}
