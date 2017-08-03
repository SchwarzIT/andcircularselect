package kaufland.com.andcircularselect.indicator;

import android.view.View;

import kaufland.com.andcircularselect.data.DataView;

/**
 * Created by sbra0902 on 03.08.17.
 */

public interface IndicatorView {

    View getView();

    void update(DataView selected);

}
