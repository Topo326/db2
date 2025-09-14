-- View 
-- Creando vista una 1
CREATE VIEW Female_consueltation AS
SELECT p.patient_name, m.medico_name,c.diagnostic, c.`date` 
FROM Patient p 
JOIN Consultation c ON p.patient_id  = c.patient_id
JOIN Medico m ON c.medico_id = m.medico_id  
WHERE p.gender = 'Femenino';

SELECT * FROM Female_consueltation;
-- Creando vista 2
CREATE VIEW Patient_with_test AS
SELECT p.patient_name, tr.description, tr.realization_date, c.`date` AS Consulting_Day
FROM TestRequest tr
JOIN Consultation c ON tr.consultation_id = c.consultation_id
JOIN Patient p ON c.patient_id = p.patient_id;

SELECT * FROM Patient_with_test;

-- Creando vista 3
CREATE VIEW Patient_history AS
SELECT p.patient_name, hd.blood_type, hd.family_history, hd.allergies, hd.chronic_diseases 
FROM HistoryDetail hd 
JOIN MedicalHistory mh ON hd.medical_history_id = mh.medical_history_id 
JOIN Patient p ON mh.patient_id = p.patient_id;

SELECT * FROM Patient_history;

-- Para modificar las vistas, podemos usar el comando ALTER VIEW
ALTER VIEW Patient_with_test AS
SELECT p.patient_name, tr.description, tr.realization_date, c.`date` AS Consulting_Day
FROM TestRequest tr
JOIN Consultation c ON tr.consultation_id = c.consultation_id
JOIN Patient p ON c.patient_id = p.patient_id
WHERE tr.realization_date >= '2023-03-01';

-- Para eliminar una vista, usamos DROP

DROP VIEW Patient_with_test;
