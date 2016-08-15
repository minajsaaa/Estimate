package com.owed.nobug.view.viewpager.indicator;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.lightsky.infiniteindicator.loader.ImageLoader;

/**
 * Created by seonjonghun on 2016. 8. 15..
 */
public class GlideLoader implements ImageLoader {

    //  =======================================================================================

    @Override
    public void initLoader(Context context) {

    }

    @Override
    public void load(Context context, ImageView targetView, Object res) {
        if (res instanceof String){
            Glide.with(context)
                    .load((String) res)
                    .centerCrop()
//                .placeholder(R.drawable.a)
                    .crossFade()
                    .into(targetView);
        }
    }
}
