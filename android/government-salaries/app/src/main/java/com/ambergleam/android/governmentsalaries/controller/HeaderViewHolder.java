package com.ambergleam.android.governmentsalaries.controller;

import android.view.View;
import android.widget.TextView;

public class HeaderViewHolder extends BaseViewHolder {

    private TextView mHeaderTextView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        mHeaderTextView = (TextView) itemView;
    }

    @Override
    public void bind(EmployeeRow employeeRow, String filterString) {
        mHeaderTextView.setText(employeeRow.getHeader());
    }

}
