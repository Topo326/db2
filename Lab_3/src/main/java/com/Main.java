package com;
import com.service.CallProcedure;
import com.service.CallCrud;

public class Main {
    public static void main(String[] args) {
    CallCrud.callInsert();
    CallProcedure.callProcedure();
    }
}
