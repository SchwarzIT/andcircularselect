package kaufland.com.andcircularselect.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.security.PrivilegedAction;
import java.util.List;

import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 02.08.17.
 */

public class CircularRenderer {


    public void drawPieces(Context context, Canvas canvas, RectF surfaceRect, List<DataView> data) {


        float startAngle = 0f;

        float sweepAngle = calcRadius(data);

        for (DataView view : data) {
            view.draw(context, canvas, surfaceRect, startAngle, sweepAngle);
            startAngle += calcRadius(data);
        }

    }

    public float calcRadius(List<DataView> data) {

        return 360f / data.size();

    }

    public void drawControl(Canvas canvas, RectF fuldrawingRectF, float outerCircleWith, int backgroundColor) {

        Paint paint = new Paint();
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);

        canvas.drawArc(new RectF(fuldrawingRectF.left + outerCircleWith, fuldrawingRectF.top + outerCircleWith, fuldrawingRectF.bottom - outerCircleWith, fuldrawingRectF.bottom- outerCircleWith), 360f, -360f, true, paint);

    }
}
