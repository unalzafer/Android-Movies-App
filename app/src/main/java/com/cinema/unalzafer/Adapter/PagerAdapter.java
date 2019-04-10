package com.cinema.unalzafer.Adapter;


import com.cinema.unalzafer.Fragment.MoviesFragment;
import com.cinema.unalzafer.Fragment.PopularMoviesFragment;
import com.cinema.unalzafer.Fragment.UpcomingFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numberPage;
    public PagerAdapter(FragmentManager fm, int numberPage){
        super(fm);
        this.numberPage=numberPage;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();
            case 1:
                return new PopularMoviesFragment();
            case 2:
                return new UpcomingFragment();
            default:
                return new MoviesFragment();
        }
    }

    @Override
    public int getCount() {
        return numberPage;
    }
}