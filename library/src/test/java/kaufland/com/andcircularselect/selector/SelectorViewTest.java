package kaufland.com.andcircularselect.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import kaufland.com.andcircularselect.BuildConfig;
import kaufland.com.andcircularselect.R;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sbra0902 on 03.08.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SelectorViewTest{

    private class ShadowSelectorView extends SelectorView{

        public ShadowSelectorView(Context context) {
            super(context);
        }


        public void triggerDispatchDraw(Canvas canvas){

            dispatchDraw(canvas);
        }

        @SuppressLint("WrongCall")
        public void triggerOnLayout(boolean changed, int left, int top, int right, int bottom){

            onLayout(changed, left, top, right, bottom);
        }

        @Override
        public Resources getResources() {

            Resources mMock = Mockito.mock(Resources.class);
            when(mMock.getDimension(R.dimen.default_selector_size)).thenReturn(20f);

            return mMock;
        }

    }


    @Test
    public void testDispatchDraw(){

        Canvas canvas = Mockito.mock(Canvas.class);
        ShadowSelectorView view = new ShadowSelectorView(RuntimeEnvironment.application);
        view.setIndicatorPoint(new PointF(0, 0));
        view.setSelectorCircleSize(20);
        view.triggerOnLayout(true, 0, 0, 500, 500);
        view.triggerDispatchDraw(canvas);

        final ArgumentCaptor<Paint> captor = ArgumentCaptor.forClass(Paint.class);

        verify(canvas).drawCircle(eq(0f), eq(0f), eq(20f), captor.capture());

        Paint paint = captor.getValue();
        Assert.assertEquals(Color.LTGRAY, paint.getColor());
        verify(canvas).drawLine(eq(0f), eq(0f), eq(250f), eq(270f), captor.capture());

    }
}
