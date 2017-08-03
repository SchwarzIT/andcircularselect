package kaufland.com.andcircularselect.renderer;

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


    public void drawPieces(Canvas canvas, RectF surfaceRect, List<DataView> data) {


        float startAngle = 0f;

        float sweepAngle = calcRadius(data);

        for (DataView view : data) {
            view.draw(canvas, surfaceRect, startAngle, sweepAngle);
            startAngle += calcRadius(data);
        }

    }

    private float calcRadius(List<DataView> data) {

        return 360f / data.size();

    }

    public void drawControl(Canvas canvas, RectF fuldrawingRectF, float outerCircleWith, int backgroundColor) {

        Paint paint = new Paint();
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);

        canvas.drawArc(new RectF(fuldrawingRectF.left + outerCircleWith, fuldrawingRectF.top + outerCircleWith, fuldrawingRectF.bottom - outerCircleWith, fuldrawingRectF.bottom- outerCircleWith), 360f, -360f, true, paint);

    }

    public void drawIndicator(Canvas canvas, RectF fuldrawingRectF, float radius) {

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);

        canvas.drawCircle(fuldrawingRectF.centerX(), fuldrawingRectF.centerY(), radius, paint);

    }
}
