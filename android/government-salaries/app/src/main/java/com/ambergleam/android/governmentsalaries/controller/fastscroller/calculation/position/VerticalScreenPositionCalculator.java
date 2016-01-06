package com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.position;


import com.ambergleam.android.governmentsalaries.controller.fastscroller.calculation.VerticalScrollBoundsProvider;

/**
 * Calculates the correct vertical Y position for a view based on scroll progress and given bounds
 */
public class VerticalScreenPositionCalculator {

    private final VerticalScrollBoundsProvider mVerticalScrollBoundsProvider;

    public VerticalScreenPositionCalculator(VerticalScrollBoundsProvider scrollBoundsProvider) {
        mVerticalScrollBoundsProvider = scrollBoundsProvider;
    }

    public float getYPositionFromScrollProgress(float scrollProgress) {
            return Math.min(
                    mVerticalScrollBoundsProvider.getMinimumScrollY() + ((mVerticalScrollBoundsProvider.getMaximumScrollY() - mVerticalScrollBoundsProvider.getMinimumScrollY()) * scrollProgress),
                    mVerticalScrollBoundsProvider.getMaximumScrollY());
    }

    public float getYPositionFromScrollProgress(float scrollProgress, int indicatorHeight, int thumbHeight) {
        return Math.max(mVerticalScrollBoundsProvider.getMinimumScrollY(),
                        (scrollProgress * (mVerticalScrollBoundsProvider.getMaximumScrollY() - thumbHeight)) - indicatorHeight + thumbHeight);
    }

}
