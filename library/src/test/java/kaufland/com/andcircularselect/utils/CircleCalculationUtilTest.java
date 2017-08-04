package kaufland.com.andcircularselect.utils;

import android.graphics.PointF;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import kaufland.com.andcircularselect.BuildConfig;

/**
 * Created by sbra0902 on 04.08.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CircleCalculationUtilTest {


    @Test
    public void testMoveTouchedPointToCircleOutline(){

        PointF clicked = new PointF(300, 300);
        PointF center = new PointF(500, 500);
        int radius = 500;

        PointF mPointF = CircleCalculationUtil.moveTouchedPointToCircleOutline(clicked, center, radius);

        Assert.assertEquals(146.44661f, mPointF.x, 0);
        Assert.assertEquals(146.44661f, mPointF.y, 0);
    }

    @Test
    public void testAngleBetween2Lines(){

        PointF a1 = new PointF(0, 0);
        PointF a2 = new PointF(0, 500);

        PointF b1 = new PointF(0, 500);
        PointF b2 = new PointF(300, 500);

        float anger = CircleCalculationUtil.angleBetween2Lines(a1, a2, b1, b2);

        Assert.assertEquals(270f, anger, 0);
    }

    @Test
    public void testCalcPointOnCircleOutlineByAngle(){

        float anger = 90;
        PointF center = new PointF(500, 500);
        int radius = 500;

        PointF mPointF = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(anger, center, radius);

        Assert.assertEquals(500, mPointF.x, 0);
        Assert.assertEquals(1000, mPointF.y, 0);
    }

}
