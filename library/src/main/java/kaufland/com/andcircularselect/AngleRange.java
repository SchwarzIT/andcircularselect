package kaufland.com.andcircularselect;

import android.util.Log;

/**
 * Created by sbra0902 on 10.08.17.
 */

public class AngleRange {

    private Float mLowerAngle;

    private Float mUpperAngle;

    public AngleRange(Float lowerAngle, Float upperAngle) {
        mLowerAngle = lowerAngle;
        mUpperAngle = upperAngle;
    }

    public boolean matches(Float angle){
        return angle >= mLowerAngle && angle <= mUpperAngle;
    }

    public Float getCenter(){
        return (mLowerAngle + mUpperAngle) / 2;
    }
}
