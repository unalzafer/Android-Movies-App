package com.cinema.unalzafer.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.cinema.unalzafer.Adapter.PopularMoviesAdapter;
import com.cinema.unalzafer.Base.BaseFragment;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;

import java.util.ArrayList;
import java.util.List;

import static com.cinema.unalzafer.Base.BaseActivity.LATEST_MOVIES;
import static com.cinema.unalzafer.Base.BaseActivity.UPCOMING_MOVIES;


public class MoviesFragment extends BaseFragment {

    View view;
    RecyclerView rvLatestMovies;
    LinearLayoutManager layoutManager;
    PopularMoviesAdapter adapter;
    List<PopularMovies> list;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount,totalPages,pages=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_movies, container, false);

        rvLatestMovies=(RecyclerView)view.findViewById(R.id.rvLatestMovies);
        layoutManager =new LinearLayoutManager(getActivity());
        rvLatestMovies.setLayoutManager(layoutManager);
        list=new ArrayList<>();


        retrofitHelper.getLatestMovies(LATEST_MOVIES,pages);


        rvLatestMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                                retrofitHelper.getLatestMovies(LATEST_MOVIES,pages);
                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void getResponseBody(Object model, int ii) {
        if(ii==LATEST_MOVIES){

            PopularMoviesResponses m=(PopularMoviesResponses)model;
            totalPages=Integer.parseInt(m.getTotal_pages());
            list.addAll(m.getResults());
            if(m.getResults()!=null) {
                adapter = new PopularMoviesAdapter(list);
                rvLatestMovies.setAdapter(adapter);
            }


        }
    }
}
