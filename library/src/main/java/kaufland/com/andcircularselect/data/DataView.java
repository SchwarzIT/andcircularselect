package kaufland.com.andcircularselect.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by sbra0902 on 03.08.17.
 */

public interface DataView {

    void draw(Context context, Canvas canvas, RectF drawingArea, float startAngle, float sweepAngle);

    void onClick();

}
