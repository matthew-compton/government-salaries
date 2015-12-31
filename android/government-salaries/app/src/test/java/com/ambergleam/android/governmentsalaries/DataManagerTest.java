package com.ambergleam.android.governmentsalaries;

import com.ambergleam.android.governmentsalaries.model.DataManager;
import com.ambergleam.android.governmentsalaries.model.Employee;
import com.ambergleam.android.governmentsalaries.model.MockDataManager;
import com.ambergleam.android.governmentsalaries.model.MockEmployee;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

public class DataManagerTest {

    private static final int mSize = 3;

    private DataManager mDataManager;
    private Employee mEmployee;

    @Before
    public void setup() {
        mDataManager = new MockDataManager(mSize);
        mEmployee = new MockEmployee();
    }

    @Test
    public void testSize() {
        List<Employee> employees = mDataManager.getEmployees();
        assertThat(employees.size()).isEqualTo(mSize);
    }

    @Test
    public void testEmployees() {
        List<Employee> employees = mDataManager.getEmployees();
        for (int i = 0; i < mSize; i++) {
            assertThat(employees.get(i)).isEqualTo(mEmployee);
        }
    }

}
