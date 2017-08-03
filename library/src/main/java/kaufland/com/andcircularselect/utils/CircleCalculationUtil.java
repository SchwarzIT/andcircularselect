package kaufland.com.andcircularselect.utils;

import android.graphics.PointF;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class CircleCalculationUtil {


    public static PointF moveTouchedPointToCircleOutline(PointF clicked, PointF center, float radius){
        PointF x = new PointF(clicked.x - center.x, clicked.y - center.y);
        PointF z = new PointF(1, 0);

        float upper = (x.x * z.x) + (x.y * z.y);
        float down1 = (float) Math.sqrt(x.x*x.x + x.y*x.y);
        float down2 = (float) Math.sqrt(z.x*z.x + z.y*z.y);

        float arg = upper/(down1*down2);

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

}
