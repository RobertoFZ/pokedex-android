package com.roberto.pokedex.common;

import android.widget.AbsListView;

/**
 * Created by robertofz on 6/26/18.
 */

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    private static final int visibleThreshold = 5;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    public EndlessScrollListener() {
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // If the total item count is zero and the previous isn't, assume the list be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        // If it's still loading, check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn't currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
            loading = onLoadMore();
        }
    }

    // Returns true if more data is being loaded; returns false if there is no more data to load.
    public abstract boolean onLoadMore();

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't necessary
    }
}
