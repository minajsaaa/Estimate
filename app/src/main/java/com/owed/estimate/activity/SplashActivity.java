package com.owed.estimate.activity;

import android.content.Intent;
import android.os.Handler;

import com.owed.estimate.model.VersionItem;
import com.owed.nobug.core.BaseActivity;
import com.owed.nobug.util.DeviceInfoUtil;
import com.owed.nobug.util.VersionUtil;
import com.owed.nobug.util.log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity implements Callback {

    private int DELAY = 10;

    //  ======================================================================================

    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    public void createChildren() {
        super.createChildren();

        requestVersion();
    }

    //  =====================================================================================

    private void requestVersion() {
        //  RetrofitBuilder.with(this, BuildConfig.).getVersion().enqueue(this);

        nextMain();
    }

    private void updateCheck(final VersionItem item) {
        /*DialogHelper.showVersionUpdate(this, item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (item.toBoolean(item.force)) {
                    finish();
                } else {
                    nextMain();
                }
            }
        });*/
    }

    private void nextMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, DELAY);
    }

    private void update(Response response) {
        try {
            VersionItem item = (VersionItem)response.body();

            if (item.toBoolean(item.maintenance)) {
               // DialogHelper.showMaintenance(this, item);
                return;
            }

            if (VersionUtil.compareVersionCode(DeviceInfoUtil.getAppVersionCode(this), item.code)) {
                updateCheck(item);
            } else {
                nextMain();
            }
        } catch (Exception e) {
            e.printStackTrace();
            nextMain();
        }
    }

    private void next(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //  =======================================================================================

    @Override
    public void onResponse(final Call call, Response response) {
        try {
            update(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(final Call call, Throwable t) {
        log.show( "onFailure : " + t.getLocalizedMessage() );
        nextMain();
    }

}
