package com.ambergleam.android.governmentsalaries.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ambergleam.android.governmentsalaries.BaseConstants;
import com.ambergleam.android.governmentsalaries.database.DatabaseHelper;
import com.ambergleam.android.governmentsalaries.database.EmployeeContentValues;
import com.ambergleam.android.governmentsalaries.database.EmployeeCursorWrapper;
import com.ambergleam.android.governmentsalaries.event.EventHelper;
import com.ambergleam.android.governmentsalaries.event.LoadDataFailureEvent;
import com.ambergleam.android.governmentsalaries.event.LoadDataSuccessEvent;
import com.ambergleam.android.governmentsalaries.utils.ConnectionUtils;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


import static com.ambergleam.android.governmentsalaries.database.DatabaseSchema.EmployeeTable;

public class LiveDataManager implements DataManager {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private Firebase mFirebaseRoot;

    public LiveDataManager(Context context) {
        mContext = context;
        mDatabase = new DatabaseHelper(mContext).getReadableDatabase();
        mFirebaseRoot = new Firebase(BaseConstants.ENDPOINT);
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        Cursor cursor = mDatabase.query(EmployeeTable.NAME, null, null, null, null, null, null);
        EmployeeCursorWrapper cursorWrapper = new EmployeeCursorWrapper(cursor);
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            employees.add(cursorWrapper.getEmployee());
            cursorWrapper.moveToNext();
        }
        cursorWrapper.close();
        return employees;
    }

    @Override
    public void update() {
        Timber.i("Updating...");
        if (!ConnectionUtils.isNetworkAvailable(mContext)) {
            // Check if cached version of database exists
            if (getDatabaseSize() > 0) {
                EventHelper.postEvent(new LoadDataSuccessEvent());
            } else {
                EventHelper.postEvent(new LoadDataFailureEvent());
            }
            return;
        }
        mFirebaseRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Timber.i("Firebase read success.");
                if (snapshot.getChildrenCount() == getDatabaseSize()) {
                    EventHelper.postEvent(new LoadDataSuccessEvent());
                } else {
                    refresh();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Timber.e("Firebase read failure: " + firebaseError.getMessage());
                EventHelper.postEvent(new LoadDataFailureEvent());
            }
        });
    }

    private long getDatabaseSize() {
        String sql = "SELECT COUNT(*) FROM " + EmployeeTable.NAME;
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        long count = statement.simpleQueryForLong();
        return count;
    }

    private void refresh() {
        Timber.i("Refreshing...");
        mFirebaseRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Timber.i("Firebase read success.");
                mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
                mDatabase.beginTransaction();
                mDatabase.delete(EmployeeTable.NAME, null, null);
                try {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Employee employee = new Employee(postSnapshot.getValue(FirebaseEmployee.class));
                        Timber.i(employee.getName());
                        mDatabase.insert(EmployeeTable.NAME, null, EmployeeContentValues.get(employee));
                    }
                    mDatabase.setTransactionSuccessful();
                    EventHelper.postEvent(new LoadDataSuccessEvent());
                } finally {
                    mDatabase.endTransaction();
                }
                mDatabase = new DatabaseHelper(mContext).getReadableDatabase();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Timber.e("Firebase read failure: " + firebaseError.getMessage());
                EventHelper.postEvent(new LoadDataFailureEvent());
            }
        });
    }

}
