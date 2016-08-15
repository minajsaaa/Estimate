package com.owed.estimate.model.company;

import com.owed.nobug.model.BaseModel;

public class CompanyItem extends BaseModel {

    private static final long serialVersionUID = -1975695086054604144L;

    //  ======================================================================================

    public int no;
    public String title;
    public String contents;
    public String image = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcT_1P8GCGpq1r0qF0Ols1M3AqgsAa5ADT7TRttkBhLjPLkyygdVPZlgnJQ";
    public int registrantNo;
    public String registrationDate;
    public int readCount;

}
