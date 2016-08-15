package com.owed.nobug.constant;

import android.content.Context;

import com.owed.estimate.R;
import com.owed.nobug.util.DeviceInfoUtil;

public class HeaderProperty {

    //  =====================================================================================

    public final static String AUTH_TOKEN = "auth-token";
    public final static String USER_AGENT = "User-Agent";

    public final static String ANDROID = "Android";

    //  =====================================================================================

    public static String getUserAgent(Context context) {
        return context.getString(R.string.app_name) + DeviceInfoUtil.getAppVersion(context) + "_android";
    }
}
