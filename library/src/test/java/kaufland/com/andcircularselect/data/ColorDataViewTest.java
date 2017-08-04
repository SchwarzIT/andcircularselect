package kaufland.com.andcircularselect.data;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import kaufland.com.andcircularselect.ReflectionUtil;

/**
 * Created by sbra0902 on 04.08.17.
 */

public class ColorDataViewTest {


    @Test
    public void testBuilder() throws NoSuchFieldException, IllegalAccessException {

        ColorDataView view = new ColorDataView.Builder().withColor(3).build();
        int color = ReflectionUtil.fieldGet(ColorDataView.class, view, "mColor");
        Assert.assertEquals(3, color);
    }

}
