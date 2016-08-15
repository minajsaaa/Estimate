package com.owed.estimate.activity.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.owed.estimate.R;
import com.owed.estimate.factory.ItemFactory;
import com.owed.nobug.core.BaseActivity;
import com.owed.nobug.core.BaseFragment;
import com.owed.nobug.view.viewpager.adapter.FragmentAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WrapperMainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener,
        Callback, View.OnClickListener, ViewPager.OnPageChangeListener {

    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected FloatingActionButton fab;
    protected Toolbar toolbar;

    protected ViewPager viewPager;
    protected FragmentAdapter fragmentAdapter;
    protected TabLayout tabLayout;

    //  =======================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void createChildren() {
        super.createChildren();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void setProperties() {
        super.setProperties();

        setupToggle();
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void configureListener() {
        super.configureListener();

        fab.setOnClickListener(this);
    }

    //  =======================================================================================

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //  ===================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_deal) {

        } else if (id == R.id.nav_cast) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //  ===================================================================================

    private void setupToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupViewPager() {
        ArrayList<BaseFragment> fragments = ItemFactory.getMainFragments(getContext());
        fragmentAdapter = new FragmentAdapter(getContext(), getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.addOnPageChangeListener(this);
    }

    private void update(Response response) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  ======================================================================================

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    //  ======================================================================================

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
