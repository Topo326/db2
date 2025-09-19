package com.service;

import com.entity.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewConsultation implements InsertInterface {
    private Consultation consultation;
    public NewConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    @Override
    public void insertMethod() throws SQLException {
        String sql = "INSERT INTO Consultation (consultation_id, date, hours, medical_room, diagnostic, patient_id, medico_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = dbconn.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, consultation.getConsultationId());
            pstmt.setDate(2, new java.sql.Date(this.consultation.getDate().getTime()));
            pstmt.setTime(3, this.consultation.getHours());
            pstmt.setString(4, this.consultation.getMedicalRoom());
            pstmt.setString(5, this.consultation.getDiagnostic());
            pstmt.setInt(6, this.consultation.getPatientId());
            pstmt.setInt(7, this.consultation.getMedicoId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Consultation inserted successfully");
            } else {
                System.out.println("Failed to insert consultation");
            }
        } catch (SQLException ex) {
            System.err.println("SQL error during insertion: " + ex.getMessage());
            throw ex;
        }
    }
}
