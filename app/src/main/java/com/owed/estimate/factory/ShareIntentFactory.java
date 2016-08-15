package com.owed.estimate.factory;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.owed.estimate.model.share.ShareItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShareIntentFactory {

    private static Context mContext;
    private static Intent intent;
    private static ShareItem shareItem;
    private static List<ResolveInfo> resInfo;
    private static List<Intent> shareIntentList;

    //  =======================================================================================

    public static void sendShare(Context context, ShareItem item) {
        mContext = context;
        shareItem = item;
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        resInfo = context.getPackageManager().queryIntentActivities(intent, 0);

        if (resInfo.isEmpty()) {
            return;
        }

        Glide.with(context).load(item.image).into(shareTarget);
    }

    private static SimpleTarget shareTarget = new SimpleTarget<Bitmap>(200, 100) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
            shareIntentList = new ArrayList<Intent>();

            for (final ResolveInfo info : resInfo) {
                Intent shareIntent = (Intent) intent.clone();

                if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
                    shareIntent.setType("text/plain");
                    shareIntent.setType("image/jpg");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+mImagePath));
                } else {
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareItem.title);
                }

                Uri bmpUri = getLocalBitmapUri(resource);
                if (bmpUri != null) {
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                }

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareItem.link);

                shareIntent.setPackage(info.activityInfo.packageName);
                shareIntentList.add(shareIntent);

                Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
                mContext.startActivity(chooserIntent);
            }
        }
    };

    private static Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

}
