package com.entity;

import java.util.Date;

public class Prescription {
    private int prescriptionId;
    private Date creationDate;
    private String durationTreatment;
    private int consultationId;

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDurationTreatment() {
        return durationTreatment;
    }

    public void setDurationTreatment(String durationTreatment) {
        this.durationTreatment = durationTreatment;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }
}
