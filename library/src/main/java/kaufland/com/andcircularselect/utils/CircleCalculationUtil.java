package kaufland.com.andcircularselect.utils;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class CircleCalculationUtil {


    public static PointF moveTouchedPointToCircleOutline(PointF clicked, PointF center, float radius) {
        PointF x = new PointF(clicked.x - center.x, clicked.y - center.y);
        PointF z = new PointF(1, 0);

        float upper = (x.x * z.x) + (x.y * z.y);
        float down1 = (float) Math.sqrt(x.x * x.x + x.y * x.y);
        float down2 = (float) Math.sqrt(z.x * z.x + z.y * z.y);

        float arg = upper / (down1 * down2);

        float alpha = (float) Math.acos(arg);

        double newPointX = center.x + (Math.cos(alpha) * radius);

        double newPointY;
        if (clicked.y < center.y) {
            newPointY = center.y - (Math.sin(alpha) * radius);

        } else {
            newPointY = center.y + (Math.sin(alpha) * radius);
        }

        return new PointF((float) newPointX, (float) newPointY);
    }

    public static float angleBetween2Lines(PointF A1, PointF A2, PointF B1, PointF B2) {
        float angle1 = (float) Math.atan2(A2.y - A1.y, A1.x - A2.x);
        float angle2 = (float) Math.atan2(B2.y - B1.y, B1.x - B2.x);
        float calculatedAngle = (float) Math.toDegrees(angle1 - angle2);
        if (calculatedAngle < 0) calculatedAngle += 360;

        return calculatedAngle;
    }

    public static PointF calcPointOnCircleOutlineByAngle(float angle, PointF center, float radius) {
        float x = (float) (radius * Math.cos(angle * Math.PI / 180F)) + center.x;
        float y = (float) (radius * Math.sin(angle * Math.PI / 180F)) + center.y;
        return new PointF(x, y);
    }

}
