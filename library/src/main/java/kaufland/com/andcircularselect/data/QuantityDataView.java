package kaufland.com.andcircularselect.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import kaufland.com.andcircularselect.R;
import kaufland.com.andcircularselect.utils.CircleCalculationUtil;

/**
 * Created by sbra0902 on 04.08.17.
 */

public class QuantityDataView implements DataView {

    private int mQuantity;

    private int selectorColor;

    private int selectorWidth;
    private int smallSelectorColor;


    @Override
    public void draw(Context context, Canvas canvas, RectF drawingArea, float startAngle, float sweepAngle) {

        PointF center = new PointF(drawingArea.centerX(), drawingArea.centerY());
        float radius = drawingArea.height() / 2;

        Paint paint = new Paint();
        paint.setColor(selectorColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(context.getResources().getDimension(R.dimen.stroke_width));

        Paint paintSmall = new Paint();
        paint.setColor(smallSelectorColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(context.getResources().getDimension(R.dimen.stroke_width));


        float pieceAngle = sweepAngle / 10;
        for (int i = 0; i<10; i++){

            float angle = startAngle + (pieceAngle * i);

            PointF startPoint = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(angle, center, radius);
            PointF endPoint = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(angle, center, radius - selectorWidth);


            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, i == 5 ? paint : paintSmall);
        }



    }

    @Override
    public void onClick() {

    }

    public static class Builder {

        private QuantityDataView view;

        public Builder() {
            view = new QuantityDataView();
            view.selectorColor = Color.BLACK;
            view.smallSelectorColor = Color.DKGRAY;
        }

        public QuantityDataView.Builder withSelectorColor(int color) {
            view.selectorColor = color;
            return this;
        }

        public QuantityDataView.Builder withSmallSelectorColor(int color) {
            view.smallSelectorColor = color;
            return this;
        }

        public QuantityDataView.Builder withQuantity(int quantity) {
            view.mQuantity = quantity;
            return this;
        }

        public QuantityDataView.Builder withSelectorWidth(int width) {
            view.selectorWidth = width;
            return this;
        }

        public QuantityDataView build() {
            return view;
        }


    }
}
