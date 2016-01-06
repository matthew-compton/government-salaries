package com.ambergleam.android.governmentsalaries.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private Orientation mOrientation;
    private int mStartOffset;
    private int mEndOffset;

    /**
     * A simple ItemDecoration used to add dividers to linear RecyclerViews
     *
     * @param divider     A Drawable for the divider to be drawn between the
     *                    children in the RecyclerView
     * @param orientation The orientation of the RecyclerView, either
     *                    Orientation.HORIZONTAL or Orientation.VERTICAL
     * @param startOffset An offset for the start of the RecyclerView in pixels
     * @param endOffset   An offset for the end of the RecyclerView in pixels
     */
    public DividerItemDecoration(Drawable divider, Orientation orientation, int startOffset, int endOffset) {
        if (divider == null) {
            throw new IllegalArgumentException("Can't pass in a null divider");
        }
        mDivider = divider;
        mOrientation = orientation;
        mStartOffset = startOffset;
        mEndOffset = endOffset;
    }

    public DividerItemDecoration(Drawable divider, Orientation orientation) {
        this(divider, orientation, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == Orientation.HORIZONTAL) {
            drawHorizontalDivider(c, parent);
        } else {
            drawVerticalDivider(c, parent);
        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent) {
        int parentTop = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int parentLeft = child.getRight() + params.rightMargin;
            int parentRight = parentLeft + mDivider.getIntrinsicWidth();

            mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            mDivider.draw(c);
        }
    }

    private void drawVerticalDivider(Canvas c, RecyclerView parent) {
        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int parentTop = child.getBottom() + params.bottomMargin;
            int parentBottom = parentTop + mDivider.getIntrinsicHeight();

            mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemCount = parent.getAdapter().getItemCount();

        drawInteriorOffsets(outRect);
        drawStartOffset(outRect, view, parent);
        drawEndOffset(outRect, view, parent, itemCount);
    }

    private void drawInteriorOffsets(Rect outRect) {
        if (mOrientation == Orientation.HORIZONTAL) {
            outRect.left = mDivider.getIntrinsicWidth();
        } else {
            outRect.top = mDivider.getIntrinsicHeight();
        }
    }

    private void drawStartOffset(Rect outRect, View view, RecyclerView parent) {
        if (parent.getChildAdapterPosition(view) < 1) {
            if (mOrientation == Orientation.HORIZONTAL) {
                outRect.left = mStartOffset;
            } else {
                outRect.top = mStartOffset;
            }
        }
    }

    private void drawEndOffset(Rect outRect, View view, RecyclerView parent, int itemCount) {
        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            if (mOrientation == Orientation.HORIZONTAL) {
                outRect.right = mEndOffset;
            } else {
                outRect.bottom = mEndOffset;
            }
        }
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

}
