package com.owed.estimate.fragment;

import com.owed.estimate.R;
import com.owed.nobug.core.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seonjonghun on 2016. 8. 14..
 */
public class HomeFragment extends BaseFragment implements Callback {




    //  =====================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.fragment_home;
    }

    //  =====================================================================================

    //  =====================================================================================

    private void request() {

    }

    private void update(Response response) {
        try {

        } catch (Exception e) {

        }
    }

    //  =====================================================================================

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }


}
