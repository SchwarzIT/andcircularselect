package kaufland.com.andcircularselect.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class ColorDataView implements DataView {

    private int mColor;


    private ColorDataView() {
        //hide constructor use Builder instead
    }


    @Override
    public void draw(Canvas canvas, RectF drawingArea, float startAngle, float sweepAngle) {

        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setAntiAlias(true);
        canvas.drawArc(drawingArea, startAngle, sweepAngle, true, paint);

    }

    @Override
    public void onClick() {

    }

    public static class Builder {

        private ColorDataView view;

        public Builder() {
            view = new ColorDataView();
        }

        public Builder withColor(int color){
            view.mColor = color;
            return this;
        }

        public ColorDataView build(){
            return view;
        }


    }


}
