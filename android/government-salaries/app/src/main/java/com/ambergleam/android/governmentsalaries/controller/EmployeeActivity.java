package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ambergleam.android.governmentsalaries.BaseToolbarActivity;
import com.ambergleam.android.governmentsalaries.model.Employee;

public class EmployeeActivity extends BaseToolbarActivity {

    public static final String EXTRA_EMPLOYEE = "EXTRA_EMPLOYEE";

    public static Intent newIntent(Context context, Employee employee) {
        Intent intent = new Intent(context, EmployeeActivity.class);
        intent.putExtra(EXTRA_EMPLOYEE, employee);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        Employee employee = (Employee) getIntent().getSerializableExtra(EXTRA_EMPLOYEE);
        return EmployeeFragment.newInstance(employee);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

}