package com.ambergleam.android.governmentsalaries.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
