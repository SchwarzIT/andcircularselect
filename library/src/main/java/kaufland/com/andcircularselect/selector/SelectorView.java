package kaufland.com.andcircularselect.selector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import kaufland.com.andcircularselect.R;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class SelectorView extends View {


    private PointF mIndicatorPoint;
    private RectF mFuldrawingRectF;
    private int mCircleSize;

    public SelectorView(Context context) {
        super(context);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (mIndicatorPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.DKGRAY);
            paint.setAlpha(80);
            canvas.drawCircle(mIndicatorPoint.x, mIndicatorPoint.y, mCircleSize, paint);


            paint.setColor(Color.LTGRAY);
            paint.setAlpha(100);
            canvas.drawCircle(mIndicatorPoint.x, mIndicatorPoint.y, getResources().getDimension(R.dimen.default_selector_size) / 2, paint);

            canvas.drawLine(mIndicatorPoint.x, mIndicatorPoint.y, mFuldrawingRectF.centerX(), mFuldrawingRectF.centerY(), paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFuldrawingRectF = new RectF(left, top, right, bottom);


    }

    public PointF getIndicatorPoint() {
        return mIndicatorPoint;
    }

    public void setIndicatorPoint(PointF indicatorPoint) {
        mIndicatorPoint = indicatorPoint;
        invalidate();
    }

    public void setCircleSize(int selectorCircleSize) {
        mCircleSize = selectorCircleSize;
    }
}
