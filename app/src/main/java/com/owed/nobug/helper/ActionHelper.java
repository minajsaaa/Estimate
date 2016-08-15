package com.owed.nobug.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.owed.nobug.util.DeviceInfoUtil;

public class ActionHelper {

    public static void showOutsideBrowser(Context context, String url) {
        if (url == null)
            return;

        String value = url.replace(" ", "");
        if ("".equals(value))
            return;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static void showMarket(Context context) {
        String marketUrl = "market://details?id=" + DeviceInfoUtil.getAppPackageName(context);
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marketUrl)));
    }

    //  ========================================================================================




}
