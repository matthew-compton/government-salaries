package com.ambergleam.android.governmentsalaries;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import com.ambergleam.android.governmentsalaries.dialog.AboutDialogFragment;
import com.ambergleam.android.governmentsalaries.dialog.LicensesDialogFragment;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Bind(R.id.activity_fragment_toolbar) public Toolbar mToolbar;

    protected abstract boolean hasBackButton();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_toolbar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupToolbar();
        setupOverflowButton();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(hasBackButton());
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton());
    }

    private void setupOverflowButton() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (NoSuchFieldException e) {
            Timber.e("Error with displaying overflow menu.", e);
        } catch (IllegalAccessException e) {
            Timber.e("Error with displaying overflow menu.", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.menu_main_licenses:
                displayLicensesDialogFragment();
                return true;
            case R.id.menu_main_about:
                displayAboutDialogFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayLicensesDialogFragment() {
        LicensesDialogFragment dialog = LicensesDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), LicensesDialogFragment.class.getSimpleName());
    }

    private void displayAboutDialogFragment() {
        AboutDialogFragment dialog = AboutDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), AboutDialogFragment.class.getSimpleName());
    }

}
