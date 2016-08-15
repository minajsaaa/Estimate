package com.owed.nobug.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class ExpandableHeightListView extends ListView {

    //  =========================================================================================

    public ExpandableHeightListView(Context context) {super(context);}
    public ExpandableHeightListView(Context context, AttributeSet attrs) {super(context, attrs);}
    public ExpandableHeightListView(Context context, AttributeSet attrs, int defStyle) {super(context, attrs, defStyle);}

    //  =========================================================================================

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

}
