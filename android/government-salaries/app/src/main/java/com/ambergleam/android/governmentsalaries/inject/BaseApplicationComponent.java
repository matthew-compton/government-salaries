package com.ambergleam.android.governmentsalaries.inject;

import com.ambergleam.android.governmentsalaries.controller.SearchResultsFragment;
import com.ambergleam.android.governmentsalaries.controller.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BaseApplicationModule.class})
public interface BaseApplicationComponent {

    void inject(SplashActivity activity);
    void inject(SearchResultsFragment fragment);

}
