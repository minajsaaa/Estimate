package com.owed.estimate.factory;

import android.content.Context;
import android.os.Bundle;

import com.owed.estimate.R;
import com.owed.estimate.fragment.CastFragment;
import com.owed.estimate.fragment.DealFragment;
import com.owed.estimate.fragment.DiaryFragment;
import com.owed.estimate.fragment.HomeFragment;
import com.owed.nobug.constant.property.BaseIntentProperty;
import com.owed.nobug.core.BaseFragment;

import java.util.ArrayList;


public class ItemFactory {


    public static ArrayList<BaseFragment> getMainFragments(Context context) {
        Bundle bundle = new Bundle();

        HomeFragment homeFragment = new HomeFragment();
        DealFragment dealFragment = new DealFragment();
        CastFragment castFragment = new CastFragment();
        DiaryFragment diaryFragment = new DiaryFragment();

        bundle.putString(BaseIntentProperty.TITLE, context.getString(R.string.home));
        homeFragment.setArguments(bundle);
        bundle.putString(BaseIntentProperty.TITLE, context.getString(R.string.deal));
        dealFragment.setArguments(bundle);
        bundle.putString(BaseIntentProperty.TITLE, context.getString(R.string.cast));
        castFragment.setArguments(bundle);
        bundle.putString(BaseIntentProperty.TITLE, context.getString(R.string.diary));
        diaryFragment.setArguments(bundle);

        ArrayList<BaseFragment> items = new ArrayList<BaseFragment>();
        items.add(homeFragment);
        items.add(dealFragment);
        items.add(castFragment);
        items.add(diaryFragment);
        return items;
    }



}
