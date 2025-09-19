package com.entity;

import java.sql.Time;
import java.util.Date;

public class Consultation {
    private int consultationId;
    private Date date;
    private java.sql.Time hours;
    private String medicalRoom;
    private String diagnostic;
    private int patientId;
    private int medicoId;

    public Consultation(int consultationId, Date date, Time hours, String medicalRoom, String diagnostic, int patientId, int medicoId) {
        this.consultationId = consultationId;
        this.date = date;
        this.hours = hours;
        this.medicalRoom = medicalRoom;
        this.diagnostic = diagnostic;
        this.patientId = patientId;
        this.medicoId = medicoId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public Date getDate() {
        return date;
    }

    public Time getHours() {
        return hours;
    }

    public String getMedicalRoom() {
        return medicalRoom;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getMedicoId() {
        return medicoId;
    }
}
