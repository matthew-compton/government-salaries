package com.ambergleam.android.governmentsalaries.controller;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambergleam.android.governmentsalaries.BR;
import com.ambergleam.android.governmentsalaries.BaseFragment;
import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.databinding.FragmentEmployeeBinding;
import com.ambergleam.android.governmentsalaries.model.Employee;
import com.ambergleam.android.governmentsalaries.view.EmployeeViewModel;

import butterknife.ButterKnife;

public class EmployeeFragment extends BaseFragment {

    private FragmentEmployeeBinding mEmployeeBinding;
    private Employee mEmployee;

    public static EmployeeFragment newInstance(Employee employee) {
        Bundle args = new Bundle();
        args.putSerializable(EmployeeActivity.EXTRA_EMPLOYEE, employee);

        EmployeeFragment fragment = new EmployeeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mEmployee = (Employee) getArguments().getSerializable(EmployeeActivity.EXTRA_EMPLOYEE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, parent, false);
        ButterKnife.bind(this, view);
        setupDataBinding(view);
        return view;
    }

    private void setupDataBinding(View view) {
        mEmployeeBinding = DataBindingUtil.bind(view);
        mEmployeeBinding.setVariable(BR.employee, new EmployeeViewModel(mEmployee));
        mEmployeeBinding.executePendingBindings();
    }

}