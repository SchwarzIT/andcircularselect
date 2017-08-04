package kaufland.com.andcircularselect.data;

import org.junit.Assert;
import org.junit.Test;

import kaufland.com.andcircularselect.ReflectionUtil;

/**
 * Created by sbra0902 on 04.08.17.
 */

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

}
