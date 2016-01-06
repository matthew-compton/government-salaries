package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.ambergleam.android.governmentsalaries.model.Employee;

import java.util.List;

public class EmployeeViewHolder extends BaseViewHolder {

    private LinearLayout mEmployeeTileContainer;
    private List<Employee> mEmployees;

    public EmployeeViewHolder(View itemView, int columns) {
        super(itemView);
        Context context = itemView.getContext();

        mEmployeeTileContainer = (LinearLayout) itemView;
        for (int i = 0; i < columns; i++) {
            EmployeeListItemView employeeListItemView = new EmployeeListItemView(context);
            LinearLayout.LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            employeeListItemView.setLayoutParams(params);
            mEmployeeTileContainer.addView(employeeListItemView);
        }
    }

    @Override
    public void bind(EmployeeRow employeeRow, String filterString) {
        mEmployees = employeeRow.getEmployees();
        int itemsSize = mEmployees.size();
        for (int i = 0; i < mEmployeeTileContainer.getChildCount(); i++) {
            EmployeeListItemView employeeListItemView = (EmployeeListItemView) mEmployeeTileContainer.getChildAt(i);
            if (itemsSize > i) {
                employeeListItemView.setVisibility(View.VISIBLE);
                employeeListItemView.setContent(mEmployees.get(i), filterString);
            } else {
                employeeListItemView.setVisibility(View.INVISIBLE);
            }
        }
    }

}