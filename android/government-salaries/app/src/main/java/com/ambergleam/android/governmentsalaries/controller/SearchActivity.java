package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ambergleam.android.governmentsalaries.BaseToolbarActivity;

public class SearchActivity extends BaseToolbarActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance();
    }

    @Override
    protected boolean hasBackButton() {
        return false;
    }

}