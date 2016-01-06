package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ambergleam.android.governmentsalaries.BR;
import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.databinding.ListItemEmployeeBinding;
import com.ambergleam.android.governmentsalaries.model.Employee;
import com.ambergleam.android.governmentsalaries.view.EmployeeViewModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EmployeeListItemView extends LinearLayout {


    @Bind(R.id.list_item_employee_name) TextView mNameTextView;

    private ListItemEmployeeBinding mEmployeeBinding;

    private Employee mEmployee;
    private String mFilterString;
    private int mHighlightColor;

    public EmployeeListItemView(Context context) {
        this(context, null);
    }

    public EmployeeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmployeeListItemView(Context context, AttributeSet attrs, int layoutId) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(layoutId, this);
        ButterKnife.bind(this);
        mEmployeeBinding = DataBindingUtil.bind(this);

        Resources resources = context.getResources();
        setBackgroundColor(resources.getColor(R.color.primary_dark));
        mHighlightColor = resources.getColor(R.color.primary_light);
    }

    public Employee getEmployee() {
        return mEmployee;
    }

    public void setFilterString(String filterString) {
        mFilterString = filterString;
        updateUI();
    }

    public void setContent(Employee employee, String filterString) {
        mEmployee = employee;
        mFilterString = filterString;
        updateUI();
    }

    protected void updateUI() {
        if (mEmployee == null) {
            return;
        }

        EmployeeViewModel employeeViewModel = new EmployeeViewModel(mEmployee);
        mEmployeeBinding.setVariable(BR.employee, employeeViewModel);
        mEmployeeBinding.setVariable(BR.rowClickListener, (View.OnClickListener) v -> {
            Intent intent = EmployeeActivity.newIntent(getContext(), mEmployee);
            getContext().startActivity(intent);
        });
        mEmployeeBinding.executePendingBindings();

        if (!TextUtils.isEmpty(mFilterString)) {
            String titleText = mEmployee.getName().toLowerCase();
            int position = titleText.indexOf(mFilterString);
            if (position >= 0) {
                Spannable spannable = new SpannableString(mEmployee.getName());
                spannable.setSpan(new ForegroundColorSpan(mHighlightColor),
                                  position, position + mFilterString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                mNameTextView.setText(spannable);
                return;
            }
        }
    }

}