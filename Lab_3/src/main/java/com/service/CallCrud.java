package com.service;

import com.entity.Consultation;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class CallCrud {
    public static void callInsert(){
        Consultation newConsultation = new Consultation(
                103,
                new Date(),
                new Time(System.currentTimeMillis()),
                "Sala 105",
                "Gripe Avial",
                15,
                12
        );

        NewConsultation service = new NewConsultation(newConsultation);
        try{
            service.insertMethod();

        } catch (SQLException ex){
            System.err.println("SQL error during insertion: " + ex.getMessage());
        }
    }
}
