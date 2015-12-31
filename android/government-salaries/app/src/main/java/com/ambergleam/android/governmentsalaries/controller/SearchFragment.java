package com.ambergleam.android.governmentsalaries.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambergleam.android.governmentsalaries.BaseFragment;
import com.ambergleam.android.governmentsalaries.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_search_button)
    public void onClickSearchButton() {
        startActivity(SearchResultsActivity.newIntent(getActivity()));
    }

}