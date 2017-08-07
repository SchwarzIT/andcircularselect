package kaufland.com.andcircularselect.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

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

    private int textSize;

    private int smallLineCount;


    @Override
    public void draw(Context context, Canvas canvas, RectF drawingArea, float startAngle, float sweepAngle) {

        PointF center = new PointF(drawingArea.centerX(), drawingArea.centerY());
        float radius = drawingArea.height() / 2;

        Paint paint = new Paint();
        paint.setColor(selectorColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(context.getResources().getDimension(R.dimen.stroke_width));
        paint.setTextSize(textSize);

        Paint paintSmall = new Paint();
        paint.setColor(smallSelectorColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(context.getResources().getDimension(R.dimen.stroke_width_small));


        float pieceAngle = sweepAngle / smallLineCount;
        for (int i = 0; i< smallLineCount; i++){

            float angle = startAngle + (pieceAngle * i);

            PointF startPoint = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(angle, center, radius);
            PointF endPoint = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(angle, center, radius - selectorWidth);

            PointF numberPoint = CircleCalculationUtil.calcPointOnCircleOutlineByAngle(angle, center, radius - (3 *selectorWidth));

            if(i == smallLineCount / 2){
                canvas.drawText(String.valueOf(mQuantity), numberPoint.x, numberPoint.y, paint);
                canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
            }else{
                canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paintSmall);
            }

        }



    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
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

        public QuantityDataView.Builder withTextSize(int size) {
            view.textSize = size;
            return this;
        }

        public QuantityDataView.Builder withSelectorWidth(int width) {
            view.selectorWidth = width;
            return this;
        }

        public QuantityDataView.Builder withSmallLineCount(int count) {
            view.smallLineCount = count;
            return this;
        }


        public QuantityDataView build() {
            return view;
        }


    }
}
