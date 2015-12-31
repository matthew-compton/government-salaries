package com.ambergleam.android.governmentsalaries.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ambergleam.android.governmentsalaries.model.Employee;

public class EmployeeCursorWrapper extends CursorWrapper {

    public EmployeeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Employee getEmployee() {
        String name = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.NAME));
        String organization = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.ORGANIZATION));
        String salary = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.SALARY));
        String title = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.TITLE));
        String travelExpenses = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.TRAVEL_EXPENSES));
        String year = getString(getColumnIndex(DatabaseSchema.EmployeeTable.Cols.YEAR));
        return new Employee(name, organization, salary, title, travelExpenses, year);
    }

}
