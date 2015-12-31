package com.ambergleam.android.governmentsalaries;

import android.app.Application;
import android.content.Context;

import com.ambergleam.android.governmentsalaries.inject.BaseApplicationComponent;
import com.ambergleam.android.governmentsalaries.inject.BaseApplicationModule;
import com.ambergleam.android.governmentsalaries.inject.DaggerBaseApplicationComponent;
import com.firebase.client.Firebase;

import timber.log.Timber;

public class BaseApplication extends Application {

    private BaseApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
        setupTimber();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void setupDagger() {
        mComponent = DaggerBaseApplicationComponent.builder()
                .baseApplicationModule(new BaseApplicationModule(this))
                .build();
    }

    public static BaseApplicationComponent component(Context context) {
        return ((BaseApplication) context.getApplicationContext()).mComponent;
    }

}
