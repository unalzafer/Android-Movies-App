package com.cinema.unalzafer.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.cinema.unalzafer.Adapter.PopularMoviesAdapter;
import com.cinema.unalzafer.Base.BaseFragment;
import com.cinema.unalzafer.Helper.RetrofitHelper;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;

import java.util.ArrayList;
import java.util.List;

import static com.cinema.unalzafer.Base.BaseActivity.LATEST_MOVIES;
import static com.cinema.unalzafer.Base.BaseActivity.POPULAR_MOVIES;

public class PopularMoviesFragment extends BaseFragment {

    View view;
    RecyclerView rvPopular;
    LinearLayoutManager layoutManager;
    PopularMoviesAdapter adapter;
    List<PopularMovies> list;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount,totalPages,pages=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_popular_movies, container, false);

        rvPopular=(RecyclerView)view.findViewById(R.id.rvPopular);
        layoutManager=new LinearLayoutManager(getActivity());
        rvPopular.setLayoutManager(layoutManager);
        list=new ArrayList<>();


        retrofitHelper.getPopularMovies(POPULAR_MOVIES,pages);

        rvPopular.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    loading=true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0)
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            pages++;
                            if(pages<totalPages)
                                retrofitHelper.getPopularMovies(POPULAR_MOVIES,pages);
                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void getResponseBody(Object model, int ii) {
        if(ii==POPULAR_MOVIES){

            PopularMoviesResponses m=(PopularMoviesResponses)model;
            totalPages=Integer.parseInt(m.getTotal_pages());
            list.addAll(m.getResults());
            if(m.getResults()!=null) {
                adapter = new PopularMoviesAdapter(list);
                rvPopular.setAdapter(adapter);
            }

        }
    }
}
