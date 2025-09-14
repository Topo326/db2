-- Trigger Example
/*
Creo primero un tabla con la que pueda registrar los cambios que se hagan
en las tablas Medico, Consultation y PatientPhone. Fuenciona para cualquier tabla a la que se le cre un trigger
para registrar los cambios.
*/
CREATE TABLE HistoryLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    alter_id INT,
    action VARCHAR(50),
    table_name VARCHAR(50),
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Ejecicios 1
DELIMITER $$
CREATE TRIGGER Medico_After_Insert
    AFTER INSERT ON Medico
    FOR EACH ROW
BEGIN
    INSERT INTO HistoryLog (alter_id, action, table_name)
    VALUES (NEW.medico_id, 'INSERT', 'Medico');
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER Consultation_After_Update
    AFTER UPDATE ON Consultation
    FOR EACH ROW
BEGIN
    INSERT INTO HistoryLog (alter_id, action, table_name)
    VALUES (NEW.consultation_id, 'UPDATE', 'Consultation');
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER Patientphone_After_Delete
    AFTER DELETE ON PatientPhone
    FOR EACH ROW
BEGIN
    INSERT INTO HistoryLog (alter_id, action, table_name)
    VALUES (OLD.patient_id, 'DELETE', 'PatientPhone');
END$$
DELIMITER ;

-- TRIGGER Before Insert for validation
DELIMITER $$
CREATE TRIGGER Medico_Before_Insert
    BEFORE INSERT ON Medico
    FOR EACH ROW
BEGIN
    IF NEW.years_experience < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: years_experience no puede ser negativo.';
    END IF;
END$$
DELIMITER ;

-- TRIGGER for save changes in Medico table
DELIMITER $$
CREATE TRIGGER Medico_Shift_After_Update
    AFTER UPDATE ON Medico
    FOR EACH ROW
BEGIN
    IF NEW.shift <> OLD.shift THEN
        INSERT INTO HistoryLog (alter_id, action, table_name)
        VALUES (NEW.medico_id, 'UPDATE_SHIFT', 'Medico');
    END IF;
END$$
DELIMITER ;

-- Testing the triggers
INSERT INTO Medico (medico_id, medico_name, gender, shift, years_experience, specialty_id)
VALUES (22,'Dr. Juan Perez', 'Masculino', 'Mañana', 10, 1);

UPDATE Consultation
SET diagnostic = 'Migrania recurrente'
WHERE consultation_id = 7;

DELETE FROM PatientPhone
WHERE patient_id = 3;

INSERT INTO Medico (medico_id, medico_name, gender, shift, years_experience, specialty_id)
VALUES (23, 'Dra. Ana Gomez', 'Femenino', 'Tarde', -3, 2);

UPDATE Medico
SET shift = 'Noche'
WHERE medico_id = 3;
