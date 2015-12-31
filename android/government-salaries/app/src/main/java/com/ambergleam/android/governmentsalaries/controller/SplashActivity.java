package com.ambergleam.android.governmentsalaries.controller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ambergleam.android.governmentsalaries.BaseApplication;
import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.event.EventHelper;
import com.ambergleam.android.governmentsalaries.event.LoadDataFailureEvent;
import com.ambergleam.android.governmentsalaries.event.LoadDataSuccessEvent;
import com.ambergleam.android.governmentsalaries.model.DataManager;

import javax.inject.Inject;

import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    @Inject DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.component(this).inject(this);
        EventHelper.registerSubscriber(this);
        mDataManager.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventHelper.unregisterSubscriber(this);
    }

    public void onEventMainThread(LoadDataSuccessEvent event) {
        Timber.i("LoadDataSuccesssEvent");
        startActivity(SearchActivity.newIntent(this));
        finish();
    }

    public void onEventMainThread(LoadDataFailureEvent event) {
        Timber.i("LoadDataFailureEvent");
        Toast.makeText(this, getString(R.string.data_load_failure), Toast.LENGTH_LONG).show();
        new Handler().postDelayed(this::finish, getResources().getInteger(R.integer.failure_delay_ms));
    }

}
