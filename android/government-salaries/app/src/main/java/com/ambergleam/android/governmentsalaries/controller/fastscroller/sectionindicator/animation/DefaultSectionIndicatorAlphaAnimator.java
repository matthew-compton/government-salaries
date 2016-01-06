package com.ambergleam.android.governmentsalaries.controller.fastscroller.sectionindicator.animation;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Utility class for animating the popup section indicator
 */
public class DefaultSectionIndicatorAlphaAnimator {

    private static final int ANIMATION_DURATION_MS = 500;

    private final View mSectionIndicatorView;
    private float mTargetAlpha = 0;

    public DefaultSectionIndicatorAlphaAnimator(View sectionIndicatorView) {
        mSectionIndicatorView = sectionIndicatorView;
        mSectionIndicatorView.setAlpha(0);
    }

    public void animateTo(float target){
        if (target == mTargetAlpha) {
            return;
        }

        ObjectAnimator alphaAnimator =
                ObjectAnimator.ofFloat(mSectionIndicatorView, "alpha", mTargetAlpha, target);
        alphaAnimator.setDuration(ANIMATION_DURATION_MS);
        alphaAnimator.start();
        mTargetAlpha = target;
    }
}
