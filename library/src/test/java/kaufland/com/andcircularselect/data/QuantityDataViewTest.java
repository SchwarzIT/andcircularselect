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
import kaufland.com.andcircularselect.renderer.CircularRenderer;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 04.08.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class QuantityDataViewTest {


    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        QuantityDataView view = new QuantityDataView.Builder()
                .withQuantity(3)
                .withTextSize(4)
                .withSelectorColor(5)
                .withSelectorWidth(6)
                .withSmallLineCount(7)
                .withSmallSelectorColor(8).build();

        Assert.assertEquals(3, ReflectionUtil.fieldGet(QuantityDataView.class, view, "mQuantity"));
        Assert.assertEquals(4, ReflectionUtil.fieldGet(QuantityDataView.class, view, "textSize"));
        Assert.assertEquals(5, ReflectionUtil.fieldGet(QuantityDataView.class, view, "selectorColor"));
        Assert.assertEquals(6, ReflectionUtil.fieldGet(QuantityDataView.class, view, "selectorWidth"));
        Assert.assertEquals(7, ReflectionUtil.fieldGet(QuantityDataView.class, view, "smallLineCount"));
        Assert.assertEquals(8, ReflectionUtil.fieldGet(QuantityDataView.class, view, "smallSelectorColor"));

    }

    @Test
    public void testDrawControl() {

        Context contextMock = Mockito.mock(Context.class);
        Resources resMock = Mockito.mock(Resources.class);
        Mockito.when(contextMock.getResources()).thenReturn(resMock);
        Mockito.when(resMock.getDimension(anyInt())).thenReturn(2f);

        Canvas canvasMock = Mockito.mock(Canvas.class);
        RectF rect = new RectF(0, 0, 150, 150);

        QuantityDataView view = new QuantityDataView.Builder()
                .withSmallLineCount(3)
                .withSmallSelectorColor(2)
                .withSelectorWidth(2)
                .withSelectorColor(5)
                .withQuantity(3)
                .withTextSize(1)
                .build();

        view.draw(contextMock, canvasMock, rect, 90, 90);

        final ArgumentCaptor<Paint> captor = ArgumentCaptor.forClass(Paint.class);
        final ArgumentCaptor<RectF> captorRect = ArgumentCaptor.forClass(RectF.class);
        final ArgumentCaptor<String> captorString = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<Integer> captorX = ArgumentCaptor.forClass(Integer.class);
        final ArgumentCaptor<Integer> captorY = ArgumentCaptor.forClass(Integer.class);

        verify(canvasMock).drawText(captorString.capture(), captorX.capture(), captorY.capture(), captor.capture());

        List<String> allQuantities = captorString.getAllValues();
        Assert.assertEquals(allQuantities.get(0), "3");

        Assert.assertEquals(captor.getValue().getColor(), 2);
        Assert.assertEquals(captor.getValue().getTextSize(), 1, 0);
    }

}
