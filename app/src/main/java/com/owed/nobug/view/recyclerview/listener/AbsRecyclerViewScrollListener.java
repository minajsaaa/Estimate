package com.owed.nobug.view.recyclerview.listener;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public abstract class AbsRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private static final int FLING_JUMP_LOW_THRESHOLD = 80;
    private static final int FLING_JUMP_HIGH_THRESHOLD = 120;

    private boolean dragging = false;

    private int currentPage = 1;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 1;
    private int visibleThreshold = 5;

    private boolean loading = true;

    private RecyclerView.LayoutManager mLayoutManager;

    private Context mContext;

    private RequestManager glide;

    //  ========================================================================================

    public AbsRecyclerViewScrollListener(LinearLayoutManager layoutManager, Context context) {
        this.mLayoutManager = layoutManager;
        this.mContext = context;
        glide = Glide.with(context);
    }

    public AbsRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public AbsRecyclerViewScrollListener(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    //  =========================================================================================

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    //  =========================================================================================

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        dragging = newState == recyclerView.SCROLL_STATE_DRAGGING;
        if( glide != null ) {
            if (glide.isPaused()) {
                if (newState == recyclerView.SCROLL_STATE_DRAGGING || newState == recyclerView.SCROLL_STATE_IDLE) {
                    glide.resumeRequests();
                }
            }
        } else {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        if( glide != null ) {
            if (!dragging) {
                int currentSpeed = Math.abs(dy);
                boolean paused = glide.isPaused();
                if (paused && currentSpeed < FLING_JUMP_LOW_THRESHOLD) {
                    glide.resumeRequests();
                } else if (!paused && FLING_JUMP_HIGH_THRESHOLD < currentSpeed) {
                    glide.pauseRequests();
                }
            }
        }

        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            showTopPositon(((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition());
            showTopPositon(((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition(), dx, dy);
        } else if (mLayoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }
    }

    //  =========================================================================================

    public abstract void onLoadMore(int page, int totalItemsCount);

    public abstract void showTopPositon(int lastVisibleItemPosition);

    public abstract void showTopPositon(int lastVisibleItemPosition, int dx, int dy);

}