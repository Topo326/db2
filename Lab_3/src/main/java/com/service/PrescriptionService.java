package com.service;

import com.entity.PrescriptionDetail;
import com.util.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionService implements CallInterface<PrescriptionDetail> {

    @Override
    public List<PrescriptionDetail> callMethod(int age) throws SQLException {
        List<PrescriptionDetail> prescriptionDetails = new ArrayList<>();
        String sql = "{CALL hospital.Get_Prescription_by_Age(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, age);

            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    PrescriptionDetail detail = new PrescriptionDetail(rs);
                    prescriptionDetails.add(detail);
                }
            }
        }
        return prescriptionDetails;
    }
}