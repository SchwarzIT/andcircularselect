package kaufland.com.andcircularselect;

import android.util.Log;

/**
 * Created by sbra0902 on 10.08.17.
 */

public class AngleRange {

    private Float mLowerAngle;

    private Float mUpperAngle;

    private boolean isUpperBreak;

    public AngleRange(Float lowerAngle, Float upperAngle) {
        mLowerAngle = lowerAngle;
        mUpperAngle = upperAngle;
        if (upperAngle > 360) {
            isUpperBreak = true;
        }
    }

    public boolean matches(Float angle) {

        if (isUpperBreak && angle + 360 <= mUpperAngle) {
            angle += 360;
        }

        return angle >= mLowerAngle && angle <= mUpperAngle;
    }

    public Float getCenter() {
        return (mLowerAngle + mUpperAngle) / 2;
    }
    
}
