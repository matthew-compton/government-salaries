package com.ambergleam.android.governmentsalaries.controller.fastscroller.vertical;

import android.content.Context;
import android.util.AttributeSet;

import com.ambergleam.android.governmentsalaries.R;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.AbsRecyclerViewFastScroller;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.RecyclerViewScroller;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.VerticalScrollBoundsProvider;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.position.VerticalScreenPositionCalculator;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.progress.TouchableScrollProgressCalculator;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.progress.VerticalLinearLayoutManagerScrollProgressCalculator;
import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.progress.VerticalScrollProgressCalculator;

public class VerticalRecyclerViewFastScroller extends AbsRecyclerViewFastScroller implements RecyclerViewScroller {

    private VerticalScrollProgressCalculator mScrollProgressCalculator;
    private VerticalScreenPositionCalculator mScreenPositionCalculator;

    public VerticalRecyclerViewFastScroller(Context context) {
        this(context, null);
    }

    public VerticalRecyclerViewFastScroller(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalRecyclerViewFastScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.view_fast_scroller_scroll_bar;
    }

    @Override
    protected TouchableScrollProgressCalculator getScrollProgressCalculator() {
        return mScrollProgressCalculator;
    }

    @Override
    public void moveHandleToPosition(float scrollProgress) {
        mHandle.setY(mScreenPositionCalculator.getYPositionFromScrollProgress(scrollProgress));
    }

    protected void onCreateScrollProgressCalculator() {
        VerticalScrollBoundsProvider boundsProvider = new VerticalScrollBoundsProvider(mBar.getY(), mBar.getY() + mBar.getHeight() - mHandle.getHeight());
        mScrollProgressCalculator = new VerticalLinearLayoutManagerScrollProgressCalculator(boundsProvider);
        mScreenPositionCalculator = new VerticalScreenPositionCalculator(boundsProvider);
    }

}
