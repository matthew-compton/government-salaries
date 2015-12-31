package com.ambergleam.android.governmentsalaries.database;

import android.content.ContentValues;

import com.ambergleam.android.governmentsalaries.model.Employee;


import static com.ambergleam.android.governmentsalaries.database.DatabaseSchema.*;

public class EmployeeContentValues {

    public static ContentValues get(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(EmployeeTable.Cols.NAME, employee.getName());
        values.put(EmployeeTable.Cols.ORGANIZATION, employee.getOrganization());
        values.put(EmployeeTable.Cols.SALARY, employee.getSalary());
        values.put(EmployeeTable.Cols.TITLE, employee.getTitle());
        values.put(EmployeeTable.Cols.TRAVEL_EXPENSES, employee.getTravelExpenses());
        values.put(EmployeeTable.Cols.YEAR, employee.getYear());
        return values;
    }

}
