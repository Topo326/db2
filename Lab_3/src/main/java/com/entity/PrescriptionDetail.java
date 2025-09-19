package com.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDetail {
    private String patientName;
    private String durationTreatment;
    private String diagnostic;

    public PrescriptionDetail(ResultSet rs) throws SQLException {
        this.patientName = rs.getString("patient_name");
        this.durationTreatment = rs.getString("duration_treatment");
        this.diagnostic = rs.getString("diagnostic");
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDurationTreatment() {
        return durationTreatment;
    }

    public String getDiagnostic() {
        return diagnostic;
    }
}


