package kaufland.com.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kaufland.com.andcircularselect.CircularSelect;
import kaufland.com.andcircularselect.data.ColorDataView;
import kaufland.com.andcircularselect.data.DataView;
import kaufland.com.andcircularselect.data.QuantityDataView;
import kaufland.com.andcircularselect.indicator.IndicatorRenderer;
import kaufland.com.andcircularselect.indicator.IndicatorViewGroup;

public class MainActivity extends AppCompatActivity {

    private CircularSelect mCircularSelect;

    private CircularSelect mCircularSelectQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircularSelect = (CircularSelect) findViewById(R.id.select);
        mCircularSelectQuantity = (CircularSelect) findViewById(R.id.selectQuantity);

        setupTestData();
        setupTestDataQuantity();
    }

    private void setupTestData() {

        final List<DataView> mdata = new ArrayList<>();

        mdata.add(new ColorDataView.Builder().withColor(Color.BLACK).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.GREEN).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.YELLOW).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.BLUE).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.MAGENTA).build());
        mdata.add(new ColorDataView.Builder().withColor(Color.LTGRAY).build());

        mCircularSelect.setData(mdata);
        mCircularSelect.setIndicatorRenderer(new IndicatorRenderer() {

            private CardView mCardView;
            private TextView mTextView;

            @Override
            public void update(DataView selected, ViewGroup parent) {

                if(mCardView == null){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View inflated = inflater.inflate(R.layout.indicator_renderer, parent);
                    mCardView = (CardView) inflated.findViewById(R.id.indicator_card);
                    mTextView = (TextView) inflated.findViewById(R.id.indicator_text);
                }

                mTextView.setText(mdata.indexOf(selected) + "");
                mCardView.setRadius(getResources().getDimension(R.dimen.rounded));
                mCardView.setCardBackgroundColor(((ColorDataView)selected).getColor());

            }
        });

    }

    private void setupTestDataQuantity() {

        final List<DataView> mdata = new ArrayList<>();

        int width = (int) getResources().getDimension(R.dimen.selector_width);
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(1).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(2).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(3).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(4).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(5).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(6).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(7).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(8).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(9).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(10).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(11).withSelectorWidth(width).build());
        mdata.add(new QuantityDataView.Builder().withSelectorColor(Color.LTGRAY).withQuantity(12).withSelectorWidth(width).build());


        mCircularSelectQuantity.setData(mdata);
        mCircularSelectQuantity.setIndicatorRenderer(new IndicatorRenderer() {

            private CardView mCardView;
            private TextView mTextView;

            @Override
            public void update(DataView selected, ViewGroup parent) {

                if(mCardView == null){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View inflated = inflater.inflate(R.layout.indicator_renderer, parent);
                    mCardView = (CardView) inflated.findViewById(R.id.indicator_card);
                    mTextView = (TextView) inflated.findViewById(R.id.indicator_text);
                }

                mCardView.setCardBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
                mTextView.setText(mdata.indexOf(selected) + "");

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mCircularSelectQuantity.select(0);
    }
}
