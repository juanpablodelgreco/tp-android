package com.example.tpsoa.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection {
        private static ConnectivityManager cm;
        private static NetworkInfo ni;

        public static boolean checkConnection(Context c) {
            cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            ni = cm.getActiveNetworkInfo();
            return (ni != null && ni.isConnected());
        }
}
