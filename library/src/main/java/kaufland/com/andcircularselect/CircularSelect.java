package kaufland.com.andcircularselect;

import android.content.Context;
import android.content.res.TypedArray;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import kaufland.com.andcircularselect.data.DataView;
import kaufland.com.andcircularselect.indicator.IndicatorRenderer;
import kaufland.com.andcircularselect.indicator.IndicatorViewGroup;
import kaufland.com.andcircularselect.renderer.CircularRenderer;
import kaufland.com.andcircularselect.selector.SelectorTouchInterceptor;
import kaufland.com.andcircularselect.selector.SelectorView;
import kaufland.com.andcircularselect.utils.CircleCalculationUtil;

/**
 * Created by sbra0902 on 02.08.17.
 */

public class CircularSelect extends FrameLayout {

    private RectF mFuldrawingRectF = new RectF();

    private List<DataView> mData = new ArrayList<>();

    private SelectorView mSelectorView;

    private IndicatorViewGroup mIndicatorView;

    private CircularRenderer mRenderer = new CircularRenderer();

    private int mOuterCircleWith;

    private int mSelectorCircleSize;

    private int mIndicatorSize;

    private SelectorTouchInterceptor mSelectorTouchInterceptor;

    public CircularSelect(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircularSelect(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mSelectorView = new SelectorView(getContext());
        addView(mSelectorView);
        mIndicatorView = new IndicatorViewGroup(getContext());
        addView(mIndicatorView);

        if (attrs != null) {
            initAttributes(attrs);
        } else {
            mOuterCircleWith = (int) getResources().getDimension(R.dimen.default_outer_circle);
            mSelectorCircleSize = (int) getResources().getDimension(R.dimen.default_selector_size);
            mIndicatorSize = (int) getResources().getDimension(R.dimen.default_indicator_size);
        }
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircularSelect);


        if (typedArray == null) {
            return;
        }

        mOuterCircleWith = (int) typedArray.getDimension(R.styleable.CircularSelect_outer_circle_width, getResources().getDimension(R.dimen.default_outer_circle));
        mSelectorCircleSize = (int) typedArray.getDimension(R.styleable.CircularSelect_selector_circle_size, getResources().getDimension(R.dimen.default_selector_size));
        mIndicatorSize = (int) typedArray.getDimension(R.styleable.CircularSelect_indicator_size, getResources().getDimension(R.dimen.default_indicator_size));

        typedArray.recycle();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if (mData != null && mData.size() > 0) {
            mRenderer.drawPieces(getContext(), canvas, mFuldrawingRectF, mData);
            mRenderer.drawControl(canvas, mFuldrawingRectF, mOuterCircleWith, Color.WHITE);
        }

        super.dispatchDraw(canvas);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFuldrawingRectF = new RectF(0 + mSelectorCircleSize, 0 + mSelectorCircleSize, getWidth() - mSelectorCircleSize, getHeight() - mSelectorCircleSize);

        if (!changed) {
            return;
        }

        layoutSelectorView();
        layoutIndicatorView();
    }


    private void layoutIndicatorView() {
        int size = mIndicatorSize * 2;
        mIndicatorView.setLayoutParams(new LayoutParams(size, size));
        mIndicatorView.setX(mFuldrawingRectF.centerX() - size / 2);
        mIndicatorView.setY(mFuldrawingRectF.centerY() - size / 2);
    }

    private void layoutSelectorView() {
        mSelectorView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSelectorView.setSelectorCircleSize(mSelectorCircleSize);
        mSelectorView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                PointF clicked = new PointF(event.getX(), event.getY());
                PointF center = new PointF(mFuldrawingRectF.centerX(), mFuldrawingRectF.centerY());
                if (mSelectorTouchInterceptor != null){
                    float angle = CircleCalculationUtil.angleBetween2Lines(new PointF(mFuldrawingRectF.centerX(), mFuldrawingRectF.left), center, clicked, center);
                    if(mSelectorTouchInterceptor.onTouch(v, event, angle)){
                       return true;
                    }
                }
                boolean upEvent = event.getAction() == MotionEvent.ACTION_UP;
                select(clicked, center, upEvent);

                return true;
            }
        });
    }

    private void select(PointF clicked, PointF center, boolean upEvent) {

        float radius = mFuldrawingRectF.height() / 2;

        PointF outerPoint = CircleCalculationUtil.moveTouchedPointToCircleOutline(clicked, center, radius);
        mSelectorView.setIndicatorPoint(outerPoint);

        float angle = CircleCalculationUtil.angleBetween2Lines(new PointF(mFuldrawingRectF.width(), mFuldrawingRectF.centerY()), center, clicked, center);

        DataView dataView = mRenderer.getDataViewForAngle(angle);

        if (mIndicatorView != null && dataView != null) {
            mIndicatorView.update(dataView);
        }

        if (upEvent && dataView != null) {
            mSelectorView.setIndicatorPoint(CircleCalculationUtil.calcPointOnCircleOutlineByAngle(mRenderer.getRangeForDataView(dataView).getCenter(), center, radius));
        }
    }

    public void select(int index) {
        select(mData.get(index));
    }

    public void select(DataView value) {

        PointF center = new PointF(mFuldrawingRectF.centerX(), mFuldrawingRectF.centerY());
        float radius = mFuldrawingRectF.height() / 2;

        select(CircleCalculationUtil.calcPointOnCircleOutlineByAngle(mRenderer.calcPieceAngle(mData) * mData.indexOf(value), center, radius), center, true);
    }

    public void setIndicatorRenderer(IndicatorRenderer renderer) {
        mIndicatorView.setRenderer(renderer);
    }

    public List<DataView> getData() {
        return mData;
    }

    public void setData(List<DataView> data) {
        mData = data;
        invalidate();
    }

    public void setOuterCircleWith(int outerCircleWith) {
        mOuterCircleWith = outerCircleWith;
        invalidate();
    }

    public void setSelectorCircleSize(int selectorCircleSize) {
        mSelectorCircleSize = selectorCircleSize;
        invalidate();
    }

    public void setIndicatorSize(int indicatorSize) {
        mIndicatorSize = indicatorSize;
        invalidate();
    }

    public void setSelectorTouchInterceptor(SelectorTouchInterceptor selectorTouchInterceptor) {
        mSelectorTouchInterceptor = selectorTouchInterceptor;
    }
}
