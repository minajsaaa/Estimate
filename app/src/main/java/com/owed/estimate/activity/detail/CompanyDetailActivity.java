package com.owed.estimate.activity.detail;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.owed.estimate.R;
import com.owed.estimate.model.company.detail.CompanyDetailGroup;
import com.owed.estimate.model.company.detail.CompanyDetailItem;
import com.owed.nobug.core.BaseActivity;
import com.owed.nobug.net.RetrofitBuilder;
import com.owed.nobug.view.viewpager.indicator.GlideLoader;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.page.Page;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailActivity extends BaseActivity implements Callback, OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private InfiniteIndicator mDefaultIndicator;

    private GoogleMap map;

    private CompanyDetailGroup group;

    //  =======================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_company_detail;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDefaultIndicator.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDefaultIndicator.start();
    }

    //  =======================================================================================

    @Override
    public void createChildren() {
        super.createChildren();

        mDefaultIndicator = (InfiniteIndicator) findViewById(R.id.indicator);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void setProperties() {
        super.setProperties();

        if( getIntent() != null ) {
            CompanyDetailItem item = (CompanyDetailItem) getIntent().getSerializableExtra(CompanyDetailItem.class.getSimpleName());
            request(item.no);
        }
    }

    @Override
    public void configureListener() {
        super.configureListener();


    }

    //  =======================================================================================

    private void request(int no) {
        RetrofitBuilder.with(getContext()).getCompanyDetail(no).enqueue(this);
    }

    private void update(Response response) {
        try {
            group = (CompanyDetailGroup) response.body();

            ArrayList<Page> mPageViews = new ArrayList<Page>();
            mPageViews.add(new Page("Page A", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/a.jpg"));
            mPageViews.add(new Page("Page B", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/b.jpg"));
            mPageViews.add(new Page("Page C", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/c.jpg"));
            mPageViews.add(new Page("Page D", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/d.jpg"));

            mDefaultIndicator.setImageLoader(new GlideLoader());
            mDefaultIndicator.addPages(mPageViews);
            mDefaultIndicator.setPosition(InfiniteIndicator.IndicatorPosition.Center_Bottom);

            mapFragment.getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  =======================================================================================

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if( map != null && group != null ) {
            if( group.data != null ) {
                LatLng point = new LatLng(group.data.lat, group.data.lng);
                map.addMarker(new MarkerOptions().position(point).title("Marker in Sydney"));
                map.moveCamera(CameraUpdateFactory.newLatLng(point));
            }
        }
    }

    //  =======================================================================================

    @Override
    public void onResponse(Call call, Response response) {
        update(response);
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    //  =======================================================================================



}
