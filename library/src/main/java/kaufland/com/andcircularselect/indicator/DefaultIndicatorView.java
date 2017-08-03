package kaufland.com.andcircularselect.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kaufland.com.andcircularselect.data.ColorDataView;
import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class DefaultIndicatorView extends AppCompatTextView implements IndicatorView {

    private RectF mFuldrawingRectF = new RectF();

    private SelectedViewChangeListener mListener;
    private boolean mRounded;

    private int mSavedColor;

    public interface SelectedViewChangeListener{
        void onChange(DataView view, DefaultIndicatorView defaultIndicatorView);
    }

    public DefaultIndicatorView(Context context) {
        super(context);
    }

    public DefaultIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        super.dispatchDraw(canvas);


        Paint paint = new Paint();
        paint.setColor(((ViewGroup)getParent()).getDrawingCacheBackgroundColor());
        paint.setAntiAlias(true);

        canvas.drawCircle(mFuldrawingRectF.centerX(), mFuldrawingRectF.centerY(), getWidth() / 2, paint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFuldrawingRectF = new RectF(left, top, right, bottom);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void update(DataView selected) {

        if(mListener != null){
            mListener.onChange(selected, this);
        }
    }

    public static class Builder {

        private DefaultIndicatorView view;

        public Builder(Context context) {
            view = new DefaultIndicatorView(context);
        }

        public Builder withRoundedCorners(){
            view.mRounded = true;
            return this;
        }

        public Builder withoutRoundedCorners(){
            view.mRounded = false;
            return this;
        }

        public Builder withChangeListener(SelectedViewChangeListener listener){
            view.mListener = listener;
            return this;
        }

        public DefaultIndicatorView build(){
            return view;
        }


    }
}
