package kaufland.com.andcircularselect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import kaufland.com.andcircularselect.data.ColorDataView;
import kaufland.com.andcircularselect.data.DataView;
import kaufland.com.andcircularselect.indicator.DefaultIndicatorView;
import kaufland.com.andcircularselect.indicator.IndicatorView;
import kaufland.com.andcircularselect.renderer.CircularRenderer;
import kaufland.com.andcircularselect.selector.SelectorView;
import kaufland.com.andcircularselect.utils.CircleCalculationUtil;

/**
 * Created by sbra0902 on 02.08.17.
 */

public class CircularSelect extends FrameLayout {

    private RectF mFuldrawingRectF = new RectF();

    private List<DataView> mData = new ArrayList<>();

    private SelectorView mSelectorView;

    private IndicatorView mIndicatorView;

    public CircularSelect(@NonNull Context context) {
        super(context);
    }

    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if (mData != null && mData.size() > 0) {
            CircularRenderer renderer = new CircularRenderer();
            renderer.drawPieces(canvas, mFuldrawingRectF, mData);
            renderer.drawControl(canvas, mFuldrawingRectF, getResources().getDimension(R.dimen.default_outer_circle), Color.WHITE);
        }


        if (mSelectorView == null) {
            mSelectorView = new SelectorView(getContext());
            addView(mSelectorView);
            mSelectorView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            mSelectorView.setIndicatorPoint(new PointF(mFuldrawingRectF.centerX(), 0));
            mSelectorView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    PointF clicked = new PointF(event.getX(), event.getY());
                    PointF center = new PointF(mFuldrawingRectF.centerX(), mFuldrawingRectF.centerY());
                    float radius = mFuldrawingRectF.height() / 2;

                    PointF outerPoint = CircleCalculationUtil.moveTouchedPointToCircleOutline(clicked, center, radius);
                    mSelectorView.setIndicatorPoint(outerPoint);

                    if(mIndicatorView != null){
                        mIndicatorView.update(mData.get((int)(CircleCalculationUtil.angleBetween2Lines(new PointF(mFuldrawingRectF.width(), mFuldrawingRectF.centerY()), center, clicked, center) / (360f / mData.size()))));
                    }


//                    mIndicatorView.updateIndicator(((ColorDataView)mData.get(0)).getColor(), "");

                    return true;
                }
            });
        }

        super.dispatchDraw(canvas);

    }

    public void setIndicatorView(IndicatorView indicatorView) {
        mIndicatorView = indicatorView;

        addView(mIndicatorView.getView());
        int size = (int) getResources().getDimension(R.dimen.default_indicator_radius) * 2;
        mIndicatorView.getView().setLayoutParams(new FrameLayout.LayoutParams(size, size));
        mIndicatorView.getView().setX(mFuldrawingRectF.centerX() - size / 2);
        mIndicatorView.getView().setY(mFuldrawingRectF.centerY() - size / 2);
        mIndicatorView.update(mData.get(0));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFuldrawingRectF = new RectF(left, top, right, bottom);


    }

    public List<DataView> getData() {
        return mData;
    }

    public void setData(List<DataView> data) {
        mData = data;
        invalidate();
    }
}
