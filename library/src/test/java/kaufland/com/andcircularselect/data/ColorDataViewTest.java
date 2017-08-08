package kaufland.com.andcircularselect.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import kaufland.com.andcircularselect.BuildConfig;
import kaufland.com.andcircularselect.ReflectionUtil;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 04.08.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ColorDataViewTest {


    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        ColorDataView view = new ColorDataView.Builder().withColor(3).build();
        int color = ReflectionUtil.fieldGet(ColorDataView.class, view, "mColor");
        Assert.assertEquals(3, color);
    }

    @Test
    public void testDrawControl() {

        Context contextMock = Mockito.mock(Context.class);
        Resources resMock = Mockito.mock(Resources.class);
        Mockito.when(contextMock.getResources()).thenReturn(resMock);
        Mockito.when(resMock.getDimension(anyInt())).thenReturn(2f);

        Canvas canvasMock = Mockito.mock(Canvas.class);
        RectF rect = new RectF(0, 0, 150, 150);

        ColorDataView view = new ColorDataView.Builder().withColor(3).build();

        view.draw(contextMock, canvasMock, rect, 90, 90);

        final ArgumentCaptor<Paint> captor = ArgumentCaptor.forClass(Paint.class);
        final ArgumentCaptor<RectF> captorRect = ArgumentCaptor.forClass(RectF.class);

        verify(canvasMock).drawArc(captorRect.capture(), eq(90f), eq(90f), eq(true), captor.capture());

        Paint paint = captor.getValue();
        Assert.assertEquals(paint.getColor(), 3);
        Assert.assertTrue(paint.isAntiAlias());

        RectF rectTest = captorRect.getValue();

        Assert.assertEquals(rectTest.left, 0, 0);
        Assert.assertEquals(rectTest.right, 150 ,0);
        Assert.assertEquals(rectTest.top, 0, 0);
        Assert.assertEquals(rectTest.bottom, 150, 0);
    }

}
