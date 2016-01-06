package com.ambergleam.android.governmentsalaries.inject;

import android.content.Context;

import com.ambergleam.android.governmentsalaries.BaseApplication;
import com.ambergleam.android.governmentsalaries.BuildConfig;
import com.ambergleam.android.governmentsalaries.model.DataManager;
import com.ambergleam.android.governmentsalaries.model.LiveDataManager;
import com.ambergleam.android.governmentsalaries.model.MockDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseApplicationModule {

    private final BaseApplication mBaseApplication;

    public BaseApplicationModule(BaseApplication application) {
        mBaseApplication = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    DataManager providesDataManager(Context context) {
        return BuildConfig.DEBUG ? new MockDataManager(1000) : new LiveDataManager(context);
    }

}
