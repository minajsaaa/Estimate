package com.owed.nobug.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

    private String SUCCESS = "SUCCESS";

    //  ====================================================================================

    public boolean hasListField() {
        try {
            return getClass().getField("list") == null ? false : true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean toBoolean(int value) {
        return (value != 0);
    }

    public boolean toBoolean(String value) {
        return ( "true" == nullToString(value) );
    }

    public String nullToString(String item) {
        return item == null ? "" : item;
    }

    public boolean isSuccess(String value) {
        return SUCCESS.equals(value.toUpperCase());
    }

}
