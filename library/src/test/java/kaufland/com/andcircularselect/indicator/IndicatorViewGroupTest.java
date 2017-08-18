package kaufland.com.andcircularselect.indicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 03.08.17.
 */

public class IndicatorViewGroupTest {

    @Test
    public void testUpdate() {

        Context context = Mockito.mock(Context.class);
        IndicatorViewGroup view = new IndicatorViewGroup(context);

        IndicatorRenderer rendererMock = Mockito.mock(IndicatorRenderer.class);

        view.setRenderer(rendererMock);
        view.update(null);

        Mockito.verify(rendererMock, Mockito.times(1)).update(null, view);
    }
}
