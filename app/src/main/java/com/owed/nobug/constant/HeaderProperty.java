package com.owed.nobug.constant;

import android.content.Context;

import com.owed.estimate.R;
import com.owed.nobug.util.DeviceInfoUtil;

public class HeaderProperty {

    //  =====================================================================================

    public final static String ACCESS_KEY = "accessKey";
    public final static String VERSION = "version";
    public final static String UUID = "uuid";
    public final static String TIME = "time";
    public final static String USER_AGENT = "User-Agent";
    public final static String CONTENT_TYPE = "Content-Type";

    public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String JSON_FORMAT = "application/json";

    public final static String ANDROID = "Android";

    public final static String API_KEY = "7d8e15d5-5d0e-4bdd-86f5-d8e3084c09b2";

    //  =====================================================================================

    public static String getUserAgent(Context context) {
        return context.getString(R.string.app_name) + DeviceInfoUtil.getAppVersion(context) + "_android";
    }
}
