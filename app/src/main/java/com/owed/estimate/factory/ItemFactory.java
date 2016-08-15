package com.owed.estimate.factory;

import android.content.Context;
import android.os.Bundle;

import com.owed.estimate.fragment.CastFragment;
import com.owed.estimate.fragment.DealFragment;
import com.owed.estimate.fragment.HomeFragment;
import com.owed.nobug.core.BaseFragment;

import java.util.ArrayList;


public class ItemFactory {

    public static ArrayList<BaseFragment> getMainFragments(Context context) {
        Bundle bundle = new Bundle();

        HomeFragment homeFragment = new HomeFragment();
        DealFragment dealFragment = new DealFragment();
        CastFragment castFragment = new CastFragment();

        homeFragment.setArguments(bundle);
        dealFragment.setArguments(bundle);
        castFragment.setArguments(bundle);

        ArrayList<BaseFragment> items = new ArrayList<BaseFragment>();
        items.add(homeFragment);
        items.add(dealFragment);
        items.add(castFragment);
        return items;
    }



}
