package com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.progress;

import android.view.MotionEvent;


/**
 * Assists in calculating the amount of scroll progress for a {@link android.support.v7.widget.RecyclerView} based on a {@link android.view.MotionEvent}
 */
public interface TouchableScrollProgressCalculator extends ScrollProgressCalculator {

    /**
     * Calculates the scroll progress of a RecyclerView based on a motion event from a scroller
     * @param event for which to calculate scroll progress
     * @return fraction from [0 to 1] representing the scroll progress
     */
    public float calculateScrollProgress(MotionEvent event);

}
