package kaufland.com.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import kaufland.com.andcircularselect.CircularSelect;
import kaufland.com.andcircularselect.data.ColorDataView;
import kaufland.com.andcircularselect.data.DataView;
import kaufland.com.andcircularselect.indicator.DefaultIndicatorView;

public class MainActivity extends AppCompatActivity {

    private CircularSelect mCircularSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircularSelect = (CircularSelect) findViewById(R.id.select);

        setupTestData();
    }

    private void setupTestData() {

        List<DataView> mdata = new ArrayList<>();

        mdata.add(new ColorDataView.Builder().withColor(Color.BLACK).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.GREEN).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.YELLOW).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.BLUE).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.MAGENTA).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.LTGRAY).build());

        mCircularSelect.setData(mdata);
        mCircularSelect.setIndicatorView(new DefaultIndicatorView.Builder(this).withRoundedCorners().withChangeListener(new DefaultIndicatorView.SelectedViewChangeListener() {
            @Override
            public void onChange(DataView view, DefaultIndicatorView defaultIndicatorView) {
                defaultIndicatorView.setBackgroundColor(((ColorDataView)view).getColor());
            }
        }).build());

    }
}
