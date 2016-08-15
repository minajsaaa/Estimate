package com.owed.nobug.manager.analytics;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.owed.estimate.R;
import com.owed.nobug.util.DeviceInfoUtil;

public class GoogleAnalyticsManager {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;
    private static String propertyId;

    //  =======================================================================================

    public static void initialize(String id) {
        propertyId = id;
    }

    public static void sendScreen(Context context, String screenName) {
        Tracker tracker = getTracker(context);

        try {
            tracker.setAppName(context.getString(R.string.app_name));
            tracker.setAppVersion(DeviceInfoUtil.getAppVersion(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static void sendEvent(Context context, String category, String action, String label) {
        Tracker tracker = getTracker(context);
        tracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }

    //  ========================================================================================

    private static Tracker getTracker(Context context) {
        if (googleAnalytics == null || tracker == null) {
            googleAnalytics = GoogleAnalytics.getInstance(context);
            tracker = googleAnalytics.newTracker(propertyId);
        }
        return tracker;
    }

}
