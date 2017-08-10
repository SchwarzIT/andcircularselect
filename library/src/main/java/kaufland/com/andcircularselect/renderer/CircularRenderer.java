package kaufland.com.andcircularselect.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaufland.com.andcircularselect.AngleRange;
import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 02.08.17.
 */

public class CircularRenderer {

    Map<DataView, AngleRange> mDataViewAngleRange = new HashMap<>();


    public void drawPieces(Context context, Canvas canvas, RectF surfaceRect, List<DataView> data) {


        mDataViewAngleRange.clear();
        float startAngle = 270f - calcPieceAngle(data) / 2;

        float sweepAngle = calcPieceAngle(data);

        for (DataView view : data) {

            mDataViewAngleRange.put(view, new AngleRange(startAngle, startAngle + sweepAngle));
            view.draw(context, canvas, surfaceRect, startAngle, sweepAngle);

            startAngle = (startAngle + sweepAngle) % 360;
        }

    }

    public float calcPieceAngle(List<DataView> data) {

        return 360f / data.size();

    }

    public DataView getDataViewForAngle(float angle){

        for (Map.Entry<DataView, AngleRange> entry : mDataViewAngleRange.entrySet()) {

            if(entry.getValue().matches(angle)){
                return entry.getKey();
            }
        }

        return null;

    }

    public AngleRange getRangeForDataView(DataView view){
       return mDataViewAngleRange.get(view);
    }

    public void drawControl(Canvas canvas, RectF fuldrawingRectF, float outerCircleWith, int backgroundColor) {

        Paint paint = new Paint();
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);

        canvas.drawArc(new RectF(fuldrawingRectF.left + outerCircleWith, fuldrawingRectF.top + outerCircleWith, fuldrawingRectF.bottom - outerCircleWith, fuldrawingRectF.bottom- outerCircleWith), 360f, -360f, true, paint);

    }

    public Map<DataView, AngleRange> getAngleRanges() {
        return mDataViewAngleRange;
    }
}
