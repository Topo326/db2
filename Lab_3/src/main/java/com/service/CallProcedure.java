package com.service;

import com.entity.PrescriptionDetail;
import java.sql.SQLException;
import java.util.List;

public class CallProcedure {
    public static void callProcedure(){
        PrescriptionService service = new PrescriptionService();
        int ageToSearch = 18; // Aunque no hay pacientes de 18 anios

        try {
            List<PrescriptionDetail> details = service.callMethod(ageToSearch);

            if (details.isEmpty()) {
                System.out.println("No se encontraron prescripciones para pacientes de " + ageToSearch + " años.");
            } else {
                System.out.println("Prescripciones para pacientes de " + ageToSearch + " años:");
                for (PrescriptionDetail detail : details) {
                    System.out.println("  - Paciente: " + detail.getPatientName());
                    System.out.println("  - Diagnóstico: " + detail.getDiagnostic());
                    System.out.println("  - Duración del tratamiento: " + detail.getDurationTreatment());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar prescripciones: " + e.getMessage());
        }
    }
}
