package com.owed.estimate.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.owed.nobug.manager.analytics.GoogleAnalyticsManager;

public class EstimateApplication extends Application {

    //  =====================================================================================

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

       GoogleAnalyticsManager.initialize("UA-77516792-1");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

//        Fabric.with(this, new Crashlytics());
//        ConfigurationManager.setup(this);


    }


}
