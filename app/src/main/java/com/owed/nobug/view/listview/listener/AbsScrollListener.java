package com.owed.nobug.view.listview.listener;

import android.widget.AbsListView;

public abstract class AbsScrollListener implements AbsListView.OnScrollListener {

    public static boolean DIRECTION_DOWN = false;
    public static boolean DIRECTION_UP = true;

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 0;
    private int prevVisibleItem = 0;
    private boolean loading = true;

    //  =========================================================================================

    public AbsScrollListener() {
    }

    public AbsScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public AbsScrollListener(int visibleThreshold, int startPage) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    //  =========================================================================================

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loading = onLoadMore(currentPage + 1, totalItemCount);
        }

        if(prevVisibleItem != firstVisibleItem) {
            onScrolling( (prevVisibleItem < firstVisibleItem) );
        }
        prevVisibleItem = firstVisibleItem;
    }

    public abstract boolean onLoadMore(int page, int totalItemsCount);
    public abstract void onScrolling(boolean direction);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

//  ============================================================================================

}
