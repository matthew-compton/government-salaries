package com.ambergleam.android.governmentsalaries.controller;

import com.ambergleam.android.governmentsalaries.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRowCollection {

    private List<EmployeeRow> mRowList;
    private List<String> mHeaders;
    private int mColumns;

    public EmployeeRowCollection(List<Employee> employees, int columns) {
        generateRows(employees, columns);
        mColumns = columns;
    }

    public List<EmployeeRow> getRowList() {
        return mRowList;
    }

    public int getColumns() {
        return mColumns;
    }

    public List<String> getHeaders() {
        return mHeaders;
    }

    protected void generateRows(List<Employee> employees, int columns) {
        char lastChar = 0;
        int headerPosition = -1;
        mRowList = new ArrayList<>();
        mHeaders = new ArrayList<>();
        List<Employee> sublist = new ArrayList<>();
        for (Employee employee : employees) {
            char firstChar = employee.getFirstChar();
            if (firstChar != lastChar) {
                if (sublist.size() > 0) {
                    mRowList.add(new EmployeeRow(sublist, headerPosition));
                }
                String header = String.valueOf(firstChar);
                headerPosition++;
                mRowList.add(new EmployeeRow(header, headerPosition));
                mHeaders.add(header);
                lastChar = firstChar;
                sublist = new ArrayList<>();
            }
            if (sublist.size() == columns) {
                mRowList.add(new EmployeeRow(sublist, headerPosition));
                sublist = new ArrayList<>();
            }
            sublist.add(employee);
        }
        if (sublist.size() > 0) {
            mRowList.add(new EmployeeRow(sublist, headerPosition));
        }
    }

}
