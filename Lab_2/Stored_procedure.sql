-- stored Procedures
/*
Este procedimiento almacenado obtiene los detalles de los pacientes con un tipo de sangre 
específico. Recibe como parámetro el tipo de sangre y devuelve una lista de pacientes
*/
DELIMITER $$
CREATE PROCEDURE Get_bloodType_Patient(
	IN b_type VARCHAR(5)
)
BEGIN
	SELECT
		p.patient_id,
		p.patient_name,
		hd.blood_type
	FROM
		HistoryDetail hd
	JOIN 
		MedicalHistory mh ON hd.medical_history_id = mh.medical_history_id 
	JOIN 
		Patient p ON mh.patient_id = p.patient_id
	WHERE 
		hd.blood_type = b_type;
END$$
DELIMITER ;

CALL Get_bloodType_Patient('A-');
/*
Este procedimiento almacenado inserta un nuevo médico en la tabla Medico, siguiendo ciertos parametros
y validando que la especialidad exista en la tabla Specialty.
*/
DELIMITER $$
CREATE PROCEDURE Insert_medico_with_condition(
	IN p_medico_id INT,
	IN p_medico_name VARCHAR(255),
	IN p_gender VARCHAR(10),
	IN p_shift VARCHAR(50),
	IN p_years_experience INT,
	IN p_specialty_id INT
)
BEGIN
	DECLARE specialty_exists INT;
	SELECT COUNT(*) INTO specialty_exists
	FROM Specialty
	WHERE specialty_id = p_specialty_id;

	IF specialty_exists > 0 THEN
        IF p_years_experience > 5 AND p_gender = 'Femenino' THEN
			INSERT INTO Medico (medico_id, medico_name, gender, shift, years_experience, specialty_id)
			VALUES (p_medico_id, p_medico_name, p_gender, p_shift, p_years_experience, p_specialty_id);
		ELSE
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Error: El médico no cumple con los requisitos de experiencia o género.';
		END IF;
	ELSE
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: La especialidad especificada no existe en la base de datos.';
	END IF;
END$$
DELIMITER ;



CALL Insert_medico_with_condition(21, 'Dra. Elena Flores', 'Femenino', 'Mañana', 10, 1);

CALL Insert_medico_with_condition(23, 'Dr. Marco Polo', 'Masculino', 'Mañana', 12, 3);

SELECT * 
FROM Medico m 

/*
Ahora como se hacia una actualizacion de una tabla con un SP.
Podemos cambiar el turno de un medico segun su ID
*/
DELIMITER $$
CREATE PROCEDURE Update_medico_shift(
	IN p_medico_id INT,
	IN p_new_shift VARCHAR(50)
)
BEGIN
	UPDATE Medico
	SET shift = p_new_shift
	WHERE medico_id = p_medico_id;

END$$
DELIMITER ;
CALL Update_medico_shift(21, 'Tarde');