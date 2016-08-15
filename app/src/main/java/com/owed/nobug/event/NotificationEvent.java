package com.owed.nobug.event;

public class NotificationEvent {

    public static int TEST = 0;

    //  ======================================================================================

    public int type;
    public Object data;

    //  ======================================================================================

    public NotificationEvent(int type) {
        this.type = type;
    }

    public NotificationEvent(int type, Object data) {
        this.type = type;
        this.data = data;
    }

}
