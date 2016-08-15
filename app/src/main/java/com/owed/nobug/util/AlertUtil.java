package com.owed.nobug.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.owed.estimate.R;

public class AlertUtil {

    private static ProgressDialog progressDialog;

    //  ======================================================================================

    public static void alert(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setPositiveButton(R.string.alert_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setTitle(title);
        builder.setMessage(message);

        AlertDialog dialog = builder.create();
        show(context, dialog);
    }

    public static void alertPositive(Context context, String title, String message,
                                     String confirm, boolean canceled, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if( title != null ) {
            builder.setTitle(title);
        }

        builder.setMessage(message).setCancelable(false);
        String onText = confirm != null ? confirm : context.getString(R.string.alert_confirm);
        builder.setPositiveButton(onText, listener);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(canceled);
        show(context, dialog);
    }

    public static void alertNegative(Context context, String title, String message, String confirm, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if( title != null ) {
            builder.setTitle(title);
        }

        builder.setMessage(message).setCancelable(false);
        String onText = confirm != null ? confirm : context.getString(R.string.alert_confirm);
        builder.setPositiveButton(onText, listener);

        builder.setNegativeButton(R.string.alert_cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        show(context, dialog);
    }

    public static void alertOkCancel(Context context, String title, String message, String okText, String cacelText, boolean isCancel,
                                     DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(isCancel);

        if( title != null ) {
            builder.setTitle(title);
        }

        builder.setMessage(message).setCancelable(false);

        if( okListener != null ) {
            okText = okText != null ? okText : context.getString(R.string.alert_confirm);
            builder.setPositiveButton(okText, okListener);
        }

        if( cancelListener != null ) {
            cacelText = cacelText != null ? cacelText : context.getString(R.string.alert_cancel);
            builder.setNegativeButton(cacelText, cancelListener);
        }

        AlertDialog dialog = builder.create();
        show(context, dialog);
    }

    private static void show(Context context, AlertDialog dialog) {
        try {
            if ( (context != null) && (!dialog.isShowing()) ) {
                int w = DisplayUtil.getDeviceWidth(context);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = (int) (w * 0.85);
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.show();
                dialog.getWindow().setAttributes(layoutParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//  ================================================================================================

    public static void showProgressDialog(Context context) {
        showProgressDialog(context, context.getString(R.string.default_loading_comment));
    }

    public static void showProgressDialog(Context context, String value, boolean canceled) {
        dismissProgressDialog();

        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(canceled);
            progressDialog.setMessage(value != null ? value : context.getString(R.string.default_loading_comment));
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showProgressDialog(Context context, String value) {
        dismissProgressDialog();

        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(value != null ? value : context.getString(R.string.default_loading_comment));
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                if( progressDialog.isShowing() ) {
                    progressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

