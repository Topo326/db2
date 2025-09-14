-- Create database and use it
CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

-- Create table Specialty
CREATE TABLE Specialty (
  specialty_id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255)
);

-- Create table Medico
CREATE TABLE Medico (
  medico_id INT PRIMARY KEY,
  medico_name VARCHAR(255) NOT NULL,
  gender VARCHAR(10),
  shift VARCHAR(50),
  years_experience INT,
  specialty_id INT,
  FOREIGN KEY (specialty_id) REFERENCES Specialty(specialty_id)
);

-- Create table MedicoPhone
CREATE TABLE MedicoPhone (
  medico_id INT,
  phone VARCHAR(20),
  PRIMARY KEY (medico_id, phone),
  FOREIGN KEY (medico_id) REFERENCES Medico(medico_id)
);

-- Create table MedicoHours
CREATE TABLE MedicoHours (
  medico_id INT,
  hours_operation INT,
  PRIMARY KEY (medico_id),
  FOREIGN KEY (medico_id) REFERENCES Medico(medico_id)
);

-- Create table Patient
CREATE TABLE Patient (
  patient_id INT PRIMARY KEY,
  patient_name VARCHAR(255) NOT NULL,
  gender VARCHAR(10),
  date_birth DATE,
  insurance_number VARCHAR(50),
  address VARCHAR(255)
);

-- Create table PatientPhone
CREATE TABLE PatientPhone (
  patient_id INT,
  phone VARCHAR(20),
  description VARCHAR(255),
  PRIMARY KEY (patient_id, phone),
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);

-- Create table MedicalHistory
CREATE TABLE MedicalHistory (
  medical_history_id INT PRIMARY KEY,
  creation_date DATETIME,
  update_date DATETIME,
  patient_id INT,
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);

-- Create table HistoryDetail
CREATE TABLE HistoryDetail (
  detail_id INT PRIMARY KEY,
  blood_type VARCHAR(5),
  allergies TEXT,
  family_history TEXT,
  chronic_diseases TEXT,
  medical_history_id INT,
  FOREIGN KEY (medical_history_id) REFERENCES MedicalHistory(medical_history_id)
);

-- Create table Consultation
CREATE TABLE Consultation (
  consultation_id INT PRIMARY KEY,
  date DATE,
  hours TIME,
  medical_room TEXT,
  diagnostic TEXT,
  patient_id INT,
  medico_id INT,
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
  FOREIGN KEY (medico_id) REFERENCES Medico(medico_id)
);

-- Create table TestRequest
CREATE TABLE TestRequest (
  test_request_id INT PRIMARY KEY,
  realization_date DATETIME,
  score TEXT,
  description VARCHAR(255),
  type_test VARCHAR(100),
  consultation_id INT,
  FOREIGN KEY (consultation_id) REFERENCES Consultation(consultation_id)
);

-- Create table Prescription
CREATE TABLE Prescription (
  prescription_id INT PRIMARY KEY,
  creation_date DATETIME,
  duration_treatment VARCHAR(100),
  consultation_id INT,
  FOREIGN KEY (consultation_id) REFERENCES Consultation(consultation_id)
);

-- Create table Medication
CREATE TABLE Medication (
  medication_id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  indications TEXT,
  contraindications TEXT,
  expiration_date DATE,
  prescription_id INT,
  dose VARCHAR(50),
  frequency VARCHAR(50),
  FOREIGN KEY (prescription_id) REFERENCES Prescription(prescription_id)
);