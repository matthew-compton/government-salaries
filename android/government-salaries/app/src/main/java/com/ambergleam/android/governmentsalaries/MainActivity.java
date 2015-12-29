package com.ambergleam.android.governmentsalaries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup UI
        final TextView textView = (TextView) findViewById(R.id.activity_main_textview);

        // Setup Firebase
        Firebase.setAndroidContext(this);
        Firebase rootRef = new Firebase("https://government-salaries.firebaseio.com/");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                textView.setText("There are " + snapshot.getChildrenCount() + " employees.");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Employee employee = postSnapshot.getValue(Employee.class);
                    Log.i(TAG, employee.getName());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "The read failed: " + firebaseError.getMessage());
            }
        });

    }

}
