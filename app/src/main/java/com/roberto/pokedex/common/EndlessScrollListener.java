package com.roberto.pokedex.common;

import android.widget.AbsListView;

/**
 * Created by robertofz on 6/26/18.
 */

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    // The minimum number of items to have below the current scroll position
    private static final int visibleThreshold = 5;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // Control if we are still waiting for the last set of data to load.
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
            loading = onLoadMore(totalItemCount - visibleItemCount);
        }
    }

    // Defines the process for actually loading more data based on page
    // Returns true if more data is being loaded; returns false if there is no more data to load.
    public abstract boolean onLoadMore(int firstVisibleItem);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't necessary
    }
}
