package kaufland.com.andcircularselect.selector;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sbra0902 on 09.08.17.
 */

public interface SelectorTouchInterceptor {

    boolean onTouch(View v, MotionEvent event, float newTouchAngle);
}
