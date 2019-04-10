package com.cinema.unalzafer.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cinema.unalzafer.Adapter.PopularMoviesAdapter;
import com.cinema.unalzafer.Base.BaseActivity;
import com.cinema.unalzafer.Model.DetailModel;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends BaseActivity {

    RecyclerView rvFavorite;
    LinearLayoutManager layoutManager;
    PopularMoviesAdapter adapter;
    List<PopularMovies> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("My Favorites");

        rvFavorite=(RecyclerView)findViewById(R.id.rvFavorite);
        layoutManager =new LinearLayoutManager(getActivity());
        rvFavorite.setLayoutManager(layoutManager);
        list=new ArrayList<>();

        if(getReadFavorite()!=null){
            for (String s:getReadFavorite())
                retrofitHelper.getDetailMovies(4,Integer.parseInt(s));

        }
        else {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Not Favorite");
            builder.setMessage("Add to your favorites.");
            builder.setPositiveButton("OK.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }




    }

    @Override
    public void getResponseBody(Object model, int ii) {
        if(ii==4){
            PopularMovies m=(PopularMovies) model;
            list.add(m);
            if(list!=null) {
                adapter = new PopularMoviesAdapter(list);
                rvFavorite.setAdapter(adapter);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null) {
            list.clear();
            if(getReadFavorite()!=null){
                for (String s:getReadFavorite())
                    retrofitHelper.getDetailMovies(4,Integer.parseInt(s));

            }
            adapter.notifyDataSetChanged();
        }
    }
}
