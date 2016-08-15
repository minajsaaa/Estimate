package com.owed.estimate.model.company.detail;

import com.owed.nobug.model.BaseModel;

public class CompanyDetailItem extends BaseModel {

    private static final long serialVersionUID = -4762633315234157469L;

    //  ======================================================================================

    public int no;
    public String title;
    public String contents;
    public int registrantNo;
    public String registrationDate;
    public int readCount;

    public double lat;
    public double lng;

}
