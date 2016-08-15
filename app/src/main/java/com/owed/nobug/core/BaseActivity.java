package com.owed.nobug.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.owed.nobug.manager.ActivityManager;
import com.owed.nobug.manager.analytics.GoogleAnalyticsManager;
import com.owed.nobug.util.log;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private Context mContext;

    //  =======================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initialize();
        log.show("activity : " + getClass().getSimpleName().toString() );
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().remove(this);
        super.onDestroy();
    }

    //  ========================================================================================

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home: {
                finish();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

//  =============================================================================================

    @Override
    public int getLayoutContentView() {
        return 0;
    }

    @Override
    public void initialize() {
        setUp();
        createChildren();
        configureListener();
        setProperties();
    }

    @Override
    public void createChildren() {
    }

    @Override
    public void configureListener() {
    }

    @Override
    public void setProperties() {
    }

    @Override
    public void setUp() {
        if( getLayoutContentView() > 0 ) {
            setContentView(getLayoutContentView());
        }

        GoogleAnalyticsManager.sendScreen(getContext(), getClass().getSimpleName());
        ActivityManager.getInstance().add(this);
    }

    //  =======================================================================================

    protected Context getContext() {
        if( mContext == null )
            return this;
        return mContext;
    }

}
