package com.owed.estimate.activity.deal;

import com.owed.estimate.R;
import com.owed.estimate.model.cast.CastItem;
import com.owed.estimate.model.cast.detail.CastDetailGroup;
import com.owed.nobug.core.BaseActivity;
import com.owed.nobug.net.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealActivity extends BaseActivity implements Callback {


    private CastDetailGroup group;

    //  =======================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_deal;
    }

    //  =======================================================================================

    @Override
    public void createChildren() {
        super.createChildren();

    }

    @Override
    public void setProperties() {
        super.setProperties();

        if( getIntent() != null ) {
            CastItem item = (CastItem) getIntent().getSerializableExtra(CastItem.class.getSimpleName());
            request(item.no);
        }
    }

    @Override
    public void configureListener() {
    super.configureListener();


    }

    //  =======================================================================================

    private void request(int no) {
        RetrofitBuilder.with(getContext()).getCastDetail(no).enqueue(this);
    }

    private void update(Response response) {
        try {
            group = (CastDetailGroup) response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  =======================================================================================


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
