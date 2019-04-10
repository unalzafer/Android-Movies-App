package com.cinema.unalzafer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cinema.unalzafer.Adapter.PagerAdapter;
import com.cinema.unalzafer.Base.BaseActivity;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TabLayout tablLayout=(TabLayout)findViewById(R.id.tablayout);

        tablLayout.addTab(tablLayout.newTab().setText("Movies"));
        tablLayout.addTab(tablLayout.newTab().setText("Popular"));
        tablLayout.addTab(tablLayout.newTab().setText("Upcoming"));
        tablLayout.setTabGravity(tablLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tablLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablLayout));

        tablLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void getResponseBody(Object model, int ii) {



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                startActivity(new Intent(getApplicationContext(),FavoriteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
