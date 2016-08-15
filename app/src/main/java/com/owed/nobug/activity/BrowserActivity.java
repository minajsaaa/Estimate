package com.owed.nobug.activity;

import android.view.MenuItem;
import android.webkit.WebView;

import com.owed.estimate.R;
import com.owed.nobug.constant.property.BaseIntentProperty;
import com.owed.nobug.core.BaseActivity;
import com.owed.nobug.webview.Browser;
import com.owed.nobug.webview.IBrowserClientEvent;

/**
 * Created by seonjonghun on 2016. 8. 15..
 */
public class BrowserActivity extends BaseActivity implements IBrowserClientEvent {


    protected Browser browser;
    protected String title;

    //  =========================================================================================

    @Override
    public void onBackPressed() {
        historyBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home: {
                historyBack();
                return false;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //  =========================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_browser;
    }

    @Override
    public void createChildren() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        browser = (Browser) findViewById(R.id.browser);
        browser.setEventListener(this);
    }

    @Override
    public void setProperties() {
        if (getIntent() != null) {
            loadUrl(getIntent().getStringExtra(BaseIntentProperty.URL));
            title = getIntent().getStringExtra(BaseIntentProperty.TITLE);

            if (title != null) {
                getSupportActionBar().setTitle(title);
            }

        }
    }

    //  ========================================================================================

    @Override
    public void onReceivedTitle(WebView webview, String webviewTitle) {
        if (title == null) {
            getSupportActionBar().setTitle(webviewTitle);
        }
    }

    //  ========================================================================================

    protected void historyBack() {
        if (browser.canGoBack()) {
            browser.goBack();
            return;
        }

        super.onBackPressed();
    }

    private void loadUrl(String url) {
        if (url != null) {
            browser.loadUrl(url);
        }
    }

}
