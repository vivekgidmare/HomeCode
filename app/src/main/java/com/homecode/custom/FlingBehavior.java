package com.homecode.custom;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by vivek on 19/05/16.
 */
public class FlingBehavior extends AppBarLayout.Behavior {
    private static final int TOP_CHILD_FLING_THRESHOLD = 3;
    private boolean isPositive;

    public FlingBehavior(boolean isPositive) {
        this.isPositive = isPositive;
    }

    public FlingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
            velocityY = velocityY * -1;
        }
        if (target instanceof RecyclerView && velocityY < 0) {
            final RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
        } else if (target instanceof NestedScrollView && velocityY < 0) {
            final NestedScrollView scrollView = (NestedScrollView) target;
            consumed = velocityY > 0 || scrollView.computeVerticalScrollOffset() > 0;

        }

//        if (target instanceof RecyclerView) {
//            final RecyclerView recyclerView = (RecyclerView) target;
//            consumed = velocityY > 0 || recyclerView.computeVerticalScrollOffset() > 0;
//            Log.i("TAG","RecyclerView");
//        } else if (target instanceof NestedScrollView) {
//            final NestedScrollView scrollView = (NestedScrollView) target;
//            consumed = velocityY > 0 || scrollView.computeVerticalScrollOffset() > 0;
//            Log.i("TAG","Nested Scroll View");
//        }

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        isPositive = dy > 0;
    }
}
