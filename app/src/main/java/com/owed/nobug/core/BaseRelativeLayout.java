package com.owed.nobug.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class BaseRelativeLayout extends RelativeLayout implements IBaseView {

    protected View mView;

    //  ======================================================================================

    public BaseRelativeLayout(Context context) {
        super(context);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //  ======================================================================================

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initialize();
    }

    //  ======================================================================================

    @Override
    public int getLayoutContentView() {
        return 0;
    }

    @Override
    public void initialize() {
        createChildren();
        setProperties();
        configureListener();
    }

    @Override
    public void createChildren() {
        mView = inflate(getContext(), getLayoutContentView(), this);
    }

    @Override
    public void configureListener() {
    }

    @Override
    public void setProperties() {
    }

}
