package com.ambergleam.android.governmentsalaries.controller;

import com.ambergleam.android.governmentsalaries.model.Employee;

import java.util.List;

public class EmployeeRow {

    public enum EmployeeRowType {
        HEADER,
        STANDARD
    }

    private EmployeeRowType mEmployeeRowType;
    private List<Employee> mEmployees;
    private String mHeader;
    private int mHeaderPosition;

    public EmployeeRow(String header, int headerPosition) {
        mEmployeeRowType = EmployeeRowType.HEADER;
        mHeader = header;
        mHeaderPosition = headerPosition;
    }

    public EmployeeRow(List<Employee> employees, int headerPosition) {
        mEmployeeRowType = EmployeeRowType.STANDARD;
        mEmployees = employees;
        mHeaderPosition = headerPosition;
    }

    public EmployeeRowType getEmployeeRowType() {
        return mEmployeeRowType;
    }

    public List<Employee> getEmployees() {
        return mEmployees;
    }

    public String getHeader() {
        return mHeader;
    }

    public int getHeaderPosition() {
        return mHeaderPosition;
    }

}
