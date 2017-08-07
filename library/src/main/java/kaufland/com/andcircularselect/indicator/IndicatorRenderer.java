package kaufland.com.andcircularselect.indicator;

import android.view.View;
import android.view.ViewGroup;

import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 03.08.17.
 */

public interface IndicatorRenderer {

    void update(DataView selected, ViewGroup parent);

}
