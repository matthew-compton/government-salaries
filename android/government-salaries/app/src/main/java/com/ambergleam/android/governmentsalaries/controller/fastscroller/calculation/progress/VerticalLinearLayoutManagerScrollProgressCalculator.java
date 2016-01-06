package com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.progress;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ambergleam.android.governmentsalaries.controller.EmployeeAdapter;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.VerticalScrollBoundsProvider;


/**
 * Calculates scroll progress for a {@link android.support.v7.widget.RecyclerView} with a {@link android.support.v7.widget.LinearLayoutManager}
 */
public class VerticalLinearLayoutManagerScrollProgressCalculator extends VerticalScrollProgressCalculator {

    public VerticalLinearLayoutManagerScrollProgressCalculator(VerticalScrollBoundsProvider scrollBoundsProvider) {
        super(scrollBoundsProvider);
    }

    /**
     * @param recyclerView recycler that experiences a scroll event
     * @return the progress through the recycler view list content
     */
    @Override
    public float calculateScrollProgress(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        EmployeeAdapter adapter = (EmployeeAdapter) recyclerView.getAdapter();

        if (adapter.getItemCount() == 0) {
            return 0;
        }

        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        View firstVisibleView = layoutManager.findViewByPosition(firstVisiblePosition);
        int recyclerHeight = recyclerView.getHeight();

        float topPeekPercent = (float) firstVisibleView.getBottom() / (float) (firstVisibleView.getBottom() - firstVisibleView.getTop());
        topPeekPercent = Math.min(topPeekPercent, 1.0f);
        float realTopPosition = adapter.getWindowPosition(firstVisiblePosition, topPeekPercent);
        int fullSize = adapter.getFullSize();
        return realTopPosition / (fullSize - recyclerHeight);
    }

}
