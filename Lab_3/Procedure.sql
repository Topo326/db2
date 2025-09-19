DELIMITER $$
CREATE PROCEDURE Get_Prescription_by_Age(
    IN p_age INT(5)
)
BEGIN
SELECT
    pt.patient_name,
    p.duration_treatment,
    c.diagnostic
FROM
    Prescription p
        JOIN
    Consultation c ON p.consultation_id = c.consultation_id
        JOIN
    Patient pt ON c.patient_id = pt.patient_id
WHERE
    TIMESTAMPDIFF(YEAR, date_birth, CURDATE()) = p_age;

END $$
DELIMITER ;