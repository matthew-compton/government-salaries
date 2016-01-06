package com.ambergleam.android.governmentsalaries.model;

import com.ambergleam.android.governmentsalaries.event.EventHelper;
import com.ambergleam.android.governmentsalaries.event.LoadDataSuccessEvent;

import java.util.ArrayList;
import java.util.List;

public class MockDataManager implements DataManager {

    private List<Employee> mEmployees;
    private int mSize;

    public MockDataManager(int size) {
        mSize = size;
        update();
    }

    @Override
    public void update() {
        mEmployees = new ArrayList<>();
        for (int i = 0; i < mSize; i++) {
            mEmployees.add(new MockEmployee());
        }
        EventHelper.postEvent(new LoadDataSuccessEvent());
    }

    @Override
    public List<Employee> getEmployees() {
        return mEmployees;
    }

}
