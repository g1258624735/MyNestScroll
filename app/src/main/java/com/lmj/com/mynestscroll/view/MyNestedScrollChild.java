package com.lmj.com.mynestscroll.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by lmj on 2016/10/27 0027. on 下午 8:38
 * limengjie
 */
public class MyNestedScrollChild extends ScrollView implements NestedScrollingChild {
    private String Tag = "MyNestedScrollChild";
    private NestedScrollingChildHelper mScrollingChildHelper;


    public MyNestedScrollChild(Context context) {
        super(context);
        init(context);
    }

    public MyNestedScrollChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyNestedScrollChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        final ViewConfiguration configuration = ViewConfiguration.get(context);

    }

    /**
     * 设置嵌套滑动是否可用
     */
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        Log.i(Tag, "setNestedScrollingEnabled:" + enabled);
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    /**
     * 嵌套滑动是否可用
     */
    @Override
    public boolean isNestedScrollingEnabled() {
        Log.i(Tag, "isNestedScrollingEnabled");
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    /**
     * 开始嵌套滑动,
     *
     * @param axes 表示方向 有一下两种值
     *             ViewCompat.SCROLL_AXIS_HORIZONTAL 横向哈东
     *             ViewCompat.SCROLL_AXIS_VERTICAL 纵向滑动
     */
    @Override
    public boolean startNestedScroll(int axes) {

        boolean bl = getScrollingChildHelper().startNestedScroll(axes);
        Log.i(Tag, "startNestedScroll:axes=" + axes + ",bl:" + bl);
        Log.i(Tag, "hasNestedScrollingParent=" + getScrollingChildHelper().hasNestedScrollingParent());
        return bl;
    }

    /**
     * 停止嵌套滑动
     */
    @Override
    public void stopNestedScroll() {
        Log.i(Tag, "stopNestedScroll");
        getScrollingChildHelper().stopNestedScroll();
    }

    /**
     * 是否有父View 支持 嵌套滑动,  会一层层的网上寻找父View
     */
    @Override
    public boolean hasNestedScrollingParent() {
        Log.i(Tag, "hasNestedScrollingParent");
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    /**
     * 在处理滑动之后 调用
     *
     * @param dxConsumed     x轴上 被消费的距离
     * @param dyConsumed     y轴上 被消费的距离
     * @param dxUnconsumed   x轴上 未被消费的距离
     * @param dyUnconsumed   y轴上 未被消费的距离
     * @param offsetInWindow view 的移动距离
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        Log.i(Tag, "dispatchNestedScroll:dxConsumed:" + dxConsumed + "," +
                "dyConsumed:" + dyConsumed + ",dxUnconsumed:" + dxUnconsumed + ",dyUnconsumed:" +
                dyUnconsumed + ",offsetInWindow:" + offsetInWindow);
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    /**
     * 一般在滑动之前调用, 在ontouch 中计算出滑动距离, 然后 调用改 方法, 就给支持的嵌套的父View 处理滑动事件
     *
     * @param dx             x 轴上滑动的距离, 相对于上一次事件, 不是相对于 down事件的 那个距离
     * @param dy             y 轴上滑动的距离
     * @param consumed       一个数组, 可以传 一个空的 数组,  表示 x 方向 或 y 方向的事件 是否有被消费
     * @param offsetInWindow 支持嵌套滑动到额父View 消费 滑动事件后 导致 本 View 的移动距离
     * @return 支持的嵌套的父View 是否处理了 滑动事件
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.i(Tag, "dispatchNestedPreScroll:dx" + dx + ",dy:" + dy + ",consumed:" + consumed + ",offsetInWindow:" + offsetInWindow);
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    /**
     * @param velocityX x 轴上的滑动速度
     * @param velocityY y 轴上的滑动速度
     * @param consumed  是否被消费
     */
    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.i(Tag, "dispatchNestedFling:velocityX:" + velocityX + ",velocityY:" + velocityY + ",consumed:" + consumed);
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    /**
     * @param velocityX x 轴上的滑动速度
     * @param velocityY y 轴上的滑动速度
     */
    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.i(Tag, "dispatchNestedPreFling:velocityX:" + velocityX + ",velocityY:" + velocityY);
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
            mScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return mScrollingChildHelper;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + ((MyNestedScrollParent) getParent()).getTopViewHeight());
    }
}
