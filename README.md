[![](https://jitpack.io/v/Kaufland/andcircularselect.svg)](https://jitpack.io/#Kaufland/andcircularselect)
[![Build Status](https://travis-ci.org/Kaufland/andcircularselect.svg?branch=master)](https://travis-ci.org/Kaufland/andcircularselect)
[![codecov](https://codecov.io/gh/Kaufland/andcircularselect/branch/master/graph/badge.svg)](https://codecov.io/gh/Kaufland/andcircularselect)

# andcircularselect
library for customisable circular picker

## Features

* Easy to use - define in xml configure with Builder

* highly customisable. Every part of the control can be configured

## Screenshot

![](https://pictr.com/images/2017/08/11/g4sox.gif)


## Implementation


1. Add it in your root build.gradle at the end of repositories:

```
 buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
  }
  ...
  allprojects {
    repositories {
	maven { url 'https://jitpack.io' }
    }
  }
```

2. Add gradle dependency

    ```
    compile 'com.github.Kaufland:andcircularselect:0.6.2'
    ```

3. Configure library 

* XML

``` xml
    <kaufland.com.andcircularselect.CircularSelect
        android:layout_width="300dp"
        android:id="@+id/select"
        app:outer_circle_width="90dp"
        app:indicator_size="40dp"
        app:selector_circle_size="20dp"
        android:layout_marginBottom="20dp"
        android:layout_height="300dp"/>
```
  
 * Configure Data
  
  ``` java
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

                if (mCardView == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View inflated = inflater.inflate(R.layout.indicator_renderer, parent);
                    mCardView = (CardView) inflated.findViewById(R.id.indicator_card);
                    mTextView = (TextView) inflated.findViewById(R.id.indicator_text);
                }

                mTextView.setText(mdata.indexOf(selected) + "");
                mCardView.setRadius(getResources().getDimension(R.dimen.rounded));
                mCardView.setCardBackgroundColor(((ColorDataView) selected).getColor());

            }
        });
	
	 mCircularSelect.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                mCircularSelect.select(5);
            }
        }, 50);
   ```
  * Optional: Add TouchInterceptor (CircleLapsCountInterceptor counts moved laps)
  
   ``` java
    mCircularSelectQuantity.setSelectorTouchInterceptor(new CircleLapsCountInterceptor(new 	       CircleLapsCountInterceptor.LapsChangedListener() {
            @Override
            public void lapsChanged(int laps) {
                mLapsCount.setText("Laps: " + laps);
            }
        }, 0, 0, 4));
   ```
