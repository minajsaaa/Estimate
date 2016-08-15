package com.owed.nobug.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owed.nobug.constant.property.BaseIntentProperty;
import com.owed.nobug.util.log;

public abstract class BaseFragment extends Fragment implements IBaseView {

    protected View mView;
    protected String pageTitle = null;

    //  ========================================================================================

    public BaseFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = ( getLayoutContentView() > 0 ) ?
                inflater.inflate(getLayoutContentView(), container, false)
                : super.onCreateView(inflater, container, savedInstanceState);

        initialize();
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        log.show("onCreate");
    }

    //  =========================================================================================

    @Override
    public void setArguments(Bundle args) {
        pageTitle = args.getString(BaseIntentProperty.TITLE);
        super.setArguments(args);
    }

    @Override
    public int getLayoutContentView() {
        return 0;
    }

    @Override
    public void initialize() {
        setUp();
        createChildren();
        setProperties();
        configureListener();
    }

    @Override
    public void createChildren() {
    }

    @Override
    public void configureListener() {
    }

    @Override
    public void setProperties() {
    }

    @Override
    public void setUp() {
    }

    //  =========================================================================================

    public CharSequence getPageTitle() {
        return pageTitle;
    }

}
