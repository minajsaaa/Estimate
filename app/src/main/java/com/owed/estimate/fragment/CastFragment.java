package com.owed.estimate.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.owed.estimate.R;
import com.owed.estimate.adapter.cast.CastItemAdapter;
import com.owed.estimate.factory.IntentFactory;
import com.owed.estimate.model.cast.CastGroup;
import com.owed.estimate.model.cast.CastItem;
import com.owed.nobug.constant.property.ParameterProperty;
import com.owed.nobug.core.BaseFragment;
import com.owed.nobug.net.RetrofitBuilder;
import com.owed.nobug.util.ObjectUtil;
import com.owed.nobug.util.log;
import com.owed.nobug.view.recyclerview.decoration.DividerItemDecoration;
import com.owed.nobug.view.recyclerview.listener.AbsRecyclerViewScrollListener;
import com.owed.nobug.view.recyclerview.listener.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastFragment extends BaseFragment implements Callback {

    private RecyclerView recyclerView;
    private CastItemAdapter castItemAdapter;
    private GridLayoutManager layoutManager;
    private ProgressBar progressBar;

    private boolean isMore;

    //  =====================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.fragment_cast;
    }

    //  =====================================================================================

    @Override
    public void createChildren() {
        super.createChildren();

        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
    }

    @Override
    public void setProperties() {
        super.setProperties();

        setUpRecyclerView();
        request(ParameterProperty.DEFAULT_PAGE);
    }

    @Override
    public void configureListener() {
        super.configureListener();

        onItemClick();
        onScroll();
    }

    private void onItemClick() {
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), recyclerView, new RecyclerOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CastItem item = castItemAdapter.getItem(position);

                IntentFactory.showCompanyDetail(getContext(), 0, item);
            }

            @Override
            public void onItemLongClick(View v, int position) { }
        }));
    }

    private void onScroll() {
        recyclerView.addOnScrollListener(new AbsRecyclerViewScrollListener(layoutManager, getContext()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (isMore) {
                    request(page);
                }
            }

            @Override public void showTopPositon(int lastVisibleItemPosition) {}
            @Override public void showTopPositon(int lastVisibleItemPosition, int dx, int dy) {}
        });
    }

    //  =====================================================================================

    private void setUpRecyclerView() {
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        castItemAdapter = new CastItemAdapter(getActivity(), new ArrayList<CastItem>());
        recyclerView.setAdapter(castItemAdapter);
    }

    private void request(int page) {
        RetrofitBuilder.with(getContext()).getCastList(page, ParameterProperty.LIMIT).enqueue(this);
    }

    private void update(Response response) {
        try {
            CastGroup group = (CastGroup) response.body();
            castItemAdapter.addItems(group.list);
            isMore = castItemAdapter.getItemCount() < group.total;
            progressBar.setVisibility(View.GONE);
            log.show("group : " + ObjectUtil.toProperties(group));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //  =====================================================================================

    //  =====================================================================================

    @Override
    public void onResponse(Call call, Response response) {
        update(response);
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        log.show( getClass().getSimpleName() + " / " + t.getLocalizedMessage());
    }

    //  ======================================================================================

}
