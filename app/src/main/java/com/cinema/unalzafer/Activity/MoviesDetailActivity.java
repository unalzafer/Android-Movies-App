package com.cinema.unalzafer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cinema.unalzafer.Base.BaseActivity;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesDetailActivity extends BaseActivity {

    RelativeLayout rlMain;
    ImageView ivPoster,ivFavorite;
    TextView tvVote,tvTitle,tvDate,tvOver,tvPopularty;
    PopularMovies popularMovies;
    List<String> listFav =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        startInit();


    }

    private void startInit(){

        rlMain=(RelativeLayout)findViewById(R.id.rlMain);
        ivPoster=(ImageView) findViewById(R.id.ivPoster);
        ivFavorite=(ImageView) findViewById(R.id.ivFavorite);
        tvVote=(TextView) findViewById(R.id.tvVote);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        tvDate=(TextView) findViewById(R.id.tvDate);
        tvOver=(TextView) findViewById(R.id.tvOver);
        tvPopularty=(TextView) findViewById(R.id.tvPopularty);
        listFav=new ArrayList<>();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            popularMovies=(PopularMovies)bundle.getSerializable("details");
        }

        try {
            Glide.with(this).load(getString(R.string.image_load_url)+""+popularMovies.getPoster_path()).into(ivPoster);
            tvVote.setText(popularMovies.getVote_average());
            tvTitle.setText(popularMovies.getOriginal_title());
            tvDate.setText(popularMovies.getRelease_date());
            tvOver.setText(popularMovies.getOverview());
            tvPopularty.setText(popularMovies.getPopularity()+" Reviews");

        }catch (Exception e){
            e.printStackTrace();
        }


        if(getReadFavorite()!=null) {
            listFav = getReadFavorite();
            if (listFav.contains(popularMovies.getId())) {
                ivFavorite.setImageResource(R.drawable.ic_favorite);
            }
        }

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listFav!=null) {
                    if (!listFav.contains(popularMovies.getId())) {
                        ivFavorite.setImageResource(R.drawable.ic_favorite);
                        listFav.add(popularMovies.getId());
                        setReadFavorite(listFav);
                    } else {
                        ivFavorite.setImageResource(R.drawable.ic_unfavorite);
                        listFav.remove(popularMovies.getId());
                        setReadFavorite(listFav);
                    }
                }else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                    listFav.add(popularMovies.getId());
                    setReadFavorite(listFav);
                }
            }
        });
    }

    @Override
    public void getResponseBody(Object model, int ii) {

        if(ii==4){
            PopularMovies m=(PopularMovies) model;




        }

    }
}
