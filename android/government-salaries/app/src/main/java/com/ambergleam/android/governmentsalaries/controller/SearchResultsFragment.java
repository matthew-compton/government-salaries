package com.ambergleam.android.governmentsalaries.controller;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambergleam.android.governmentsalaries.BR;
import com.ambergleam.android.governmentsalaries.BaseApplication;
import com.ambergleam.android.governmentsalaries.BaseFragment;
import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.databinding.ListItemEmployeeBinding;
import com.ambergleam.android.governmentsalaries.model.DataManager;
import com.ambergleam.android.governmentsalaries.model.Employee;
import com.ambergleam.android.governmentsalaries.view.EmployeeViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsFragment extends BaseFragment {

    @Inject DataManager mDataManager;

    @Bind(R.id.fragment_search_results_recyclerview) RecyclerView mRecyclerView;

    private EmployeeAdapter mAdapter;

    public static SearchResultsFragment newInstance() {
        return new SearchResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.component(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, parent, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        mAdapter = new EmployeeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder {

        private ListItemEmployeeBinding mListItemEmployeeBinding;

        public EmployeeHolder(View rowView) {
            super(rowView);
            mListItemEmployeeBinding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {
            return mListItemEmployeeBinding;
        }

        public void bindEmployee(Employee employee) {
            EmployeeViewModel videoViewModel = new EmployeeViewModel(employee);
            mListItemEmployeeBinding.setVariable(BR.employee, videoViewModel);
            mListItemEmployeeBinding.executePendingBindings();
        }

    }

    public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {

        private List<Employee> mEmployeeList;

        public EmployeeAdapter() {
            mEmployeeList = mDataManager.getEmployees();
        }

        @Override
        public EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_employee, parent, false);
            return new EmployeeHolder(view);
        }

        @Override
        public void onBindViewHolder(EmployeeHolder holder, int position) {
            Employee video = mEmployeeList.get(position);
            holder.bindEmployee(video);
        }

        @Override
        public int getItemCount() {
            return mEmployeeList.size();
        }

    }

}