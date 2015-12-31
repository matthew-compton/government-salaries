package com.ambergleam.android.governmentsalaries.view;

import com.ambergleam.android.governmentsalaries.model.Employee;

public class EmployeeViewModel {

    private Employee mEmployee;

    public EmployeeViewModel(Employee employee) {
        mEmployee = employee;
    }

    public String getName() {
        return mEmployee.getName();
    }

    public String getOrganization() {
        return mEmployee.getOrganization();
    }

    public String getSalary() {
        return mEmployee.getSalary();
    }

    public String getTitle() {
        return mEmployee.getTitle();
    }

    public String getTravelExpenses() {
        return mEmployee.getTravelExpenses();
    }

    public String getYear() {
        return mEmployee.getYear();
    }

}
