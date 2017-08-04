package kaufland.com.andcircularselect.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import kaufland.com.andcircularselect.CircularSelect;
import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class IndicatorViewGroup extends FrameLayout {

    private IndicatorRenderer mRenderer;


    public IndicatorViewGroup(Context context) {
        super(context);
    }

    public IndicatorViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void update(DataView dataView) {

        if(mRenderer != null){
            mRenderer.update(dataView, this);
        }
    }

    public void setRenderer(IndicatorRenderer renderer) {
        mRenderer = renderer;
        invalidate();
    }
}
