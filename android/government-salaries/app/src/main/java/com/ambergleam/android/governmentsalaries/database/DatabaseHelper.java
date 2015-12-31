package com.ambergleam.android.governmentsalaries.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.ambergleam.android.governmentsalaries.database.DatabaseSchema.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EmployeeTable.NAME + "(" +
                           " _id integer primary key autoincrement, " +
                           EmployeeTable.Cols.NAME + ", " +
                           EmployeeTable.Cols.ORGANIZATION + ", " +
                           EmployeeTable.Cols.SALARY + ", " +
                           EmployeeTable.Cols.TITLE + ", " +
                           EmployeeTable.Cols.TRAVEL_EXPENSES + ", " +
                           EmployeeTable.Cols.YEAR +
                           ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nothing to see here
    }

}
