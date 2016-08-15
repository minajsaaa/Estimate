package com.owed.nobug.manager.analytics;

import android.app.Application;
import android.content.Context;

public class AnalyticsManager extends Application {

    private static AnalyticsManager instance;

    //  =========================================================================================

    public static AnalyticsManager getInstance() {
        if (instance == null) {
            instance = new AnalyticsManager();
        }
        return instance;
    }

    //  =========================================================================================

    public static void initialize(String key) {
        GoogleAnalyticsManager.initialize(key);
    }

    public static void sendEvent(Context context, String category, String action, String label) {
        FacebookAnalyticsManager.logEvent(context, category, action, label);
        GoogleAnalyticsManager.sendEvent(context, category, action, label);
    }

    public static void sendScreen(Context context, String name) {
        GoogleAnalyticsManager.sendScreen(context, name);
    }

    //  =========================================================================================

}
