package com.owed.estimate.factory;

import android.content.Context;
import android.content.Intent;

import com.owed.estimate.activity.detail.CompanyDetailActivity;
import com.owed.estimate.model.cast.CastItem;
import com.owed.nobug.activity.BrowserActivity;
import com.owed.nobug.constant.property.BaseIntentProperty;

public class IntentFactory {


    //  ======================================================================================

    public static void showCompanyDetail(Context context, int requestCode, CastItem item) {
        Intent intent = new Intent(context, CompanyDetailActivity.class);
        intent.putExtra(CastItem.class.getSimpleName(), item);
        context.startActivity(intent);
    }

    //  ======================================================================================

    public static void showBrowser(Context context, String title, String url) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(BaseIntentProperty.URL, url);
        intent.putExtra(BaseIntentProperty.TITLE, title);
        context.startActivity(intent);
    }

}
