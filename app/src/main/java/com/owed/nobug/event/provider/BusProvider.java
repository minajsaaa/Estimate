package com.owed.nobug.event.provider;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public final class BusProvider extends Bus {

    private static final Bus BUS = new Bus();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    //  =========================================================================================

    public static Bus getInstance() {
        return BUS;
    }

    //  =========================================================================================

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    BusProvider.getInstance().post(event);
                }
            });
        }
    }

}
