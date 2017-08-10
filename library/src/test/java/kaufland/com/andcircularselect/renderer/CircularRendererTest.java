package kaufland.com.andcircularselect.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import kaufland.com.andcircularselect.BuildConfig;
import kaufland.com.andcircularselect.data.DataView;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by sbra0902 on 02.08.17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CircularRendererTest {

    DataView mock1;

    DataView mock2;

    @Before
    public void init(){
        mock1 = Mockito.mock(DataView.class);
        mock2 = Mockito.mock(DataView.class);
    }

    @Test
    public void testDrawPieces() {

        Context contextMock = Mockito.mock(Context.class);
        Canvas canvasMock = Mockito.mock(Canvas.class);
        RectF rect = new RectF(0, 0, 150, 150);


        List<DataView> mocks = new ArrayList<>();
        mocks.add(mock1);
        mocks.add(mock2);

        new CircularRenderer().drawPieces(contextMock, canvasMock, rect, mocks);

        verify(mock1).draw(contextMock, canvasMock, rect, 180, 180);
        verify(mock2).draw(contextMock, canvasMock, rect, 0, 180);

    }

    @Test
    public void testCalcRadius() {

        List<DataView> mocks = new ArrayList<>();
        mocks.add(mock1);
        mocks.add(mock2);

        Assert.assertEquals(180, new CircularRenderer().calcPieceAngle(mocks), 0);

    }

    @Test
    public void testDrawControl() {

        Canvas canvasMock = Mockito.mock(Canvas.class);
        RectF rect = new RectF(0, 0, 150, 150);

        float outerCircleWith = 3;
        int backgroundColor = 2;

        new CircularRenderer().drawControl(canvasMock, rect, outerCircleWith, backgroundColor);

        final ArgumentCaptor<Paint> captor = ArgumentCaptor.forClass(Paint.class);
        final ArgumentCaptor<RectF> captorRect = ArgumentCaptor.forClass(RectF.class);

        verify(canvasMock).drawArc(captorRect.capture(), eq(360f), eq(-360f), eq(true), captor.capture());

        Paint mValue = captor.getValue();

        Assert.assertEquals(2, mValue.getColor());
        Assert.assertEquals(true, mValue.isAntiAlias());

        RectF rectCap = captorRect.getValue();

        Assert.assertEquals(3, rectCap.left, 0);
        Assert.assertEquals(3, rectCap.top, 0);
        Assert.assertEquals(147, rectCap.right, 0);
        Assert.assertEquals(147, rectCap.bottom, 0);
    }
}
