package kaufland.com.andcircularselect.selector;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import kaufland.com.andcircularselect.AngleRange;
import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 09.08.17.
 */

public class CircleLapsCountInterceptor implements SelectorTouchInterceptor {

    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;

    private Float mOldTouchAngle;

    private LapsChangedListener mLapsChangedListener;

    private int mLaps;

    private Integer mLowest;

    private Integer mHighest;

    public CircleLapsCountInterceptor(LapsChangedListener lapsChangedListener) {
        mLapsChangedListener = lapsChangedListener;
    }

    public CircleLapsCountInterceptor(LapsChangedListener lapsChangedListener, int startLaps, Integer lowest, Integer highest) {
        mLapsChangedListener = lapsChangedListener;
        mLaps = startLaps;
        mLowest = lowest;
        mHighest = highest;
    }

    public interface LapsChangedListener {
        void lapsChanged(int laps);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event, float newTouchAngle) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startClickTime = Calendar.getInstance().getTimeInMillis();
        }

        long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
        if (clickDuration > MAX_CLICK_DURATION && mOldTouchAngle != null && mLapsChangedListener != null) {
            //click event has occurred

            if (mOldTouchAngle > 320 && newTouchAngle < 50) {
                if (mHighest == null || mLaps < mHighest) {
                    mLaps++;
                    mLapsChangedListener.lapsChanged(mLaps);
                }
            }

            if (mOldTouchAngle < 50 && newTouchAngle > 320) {
                if (mLowest == null || mLaps > mLowest) {
                    mLaps--;
                    mLapsChangedListener.lapsChanged(mLaps);
                }
            }

        }

        mOldTouchAngle = newTouchAngle;

        return false;
    }
}
