package com.owed.nobug.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.owed.estimate.R;

public class NetworkUtil {

    //  ======================================================================================

    public static boolean isConnected(final Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (activeNetwork != null) {
            return true;
        } else {
            Toast.makeText(context, context.getString(R.string.network_error_message), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}

