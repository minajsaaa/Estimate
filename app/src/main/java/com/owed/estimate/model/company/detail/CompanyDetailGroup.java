package com.owed.estimate.model.company.detail;

import com.owed.nobug.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by seonjonghun on 2016. 8. 15..
 */
public class CompanyDetailGroup extends BaseModel {

    private static final long serialVersionUID = -5669380662030604423L;

    //  ======================================================================================

    public String result;
    public CompanyDetailItem data;
    public ArrayList<String> images;
}
