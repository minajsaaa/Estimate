package com.owed.nobug.core;

public interface IBaseView {

    int getLayoutContentView();
    void initialize();
    void createChildren();
    void configureListener();
    void setProperties();
    void setUp();
}
