package com.cinema.unalzafer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cinema.unalzafer.Activity.MoviesDetailActivity;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PMHolder> {

    List<PopularMovies> list;
    Context context;
    public PopularMoviesAdapter(List<PopularMovies> results) {
        this.list=results;
    }

    public class PMHolder extends RecyclerView.ViewHolder {

        LinearLayout llAdapterMain;
        ImageView ivPoster;
        TextView tvTitle,tvDate,tvVote,tvPopularty;
        public PMHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            ivPoster=(ImageView)itemView.findViewById(R.id.ivPoster);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);
            tvVote=(TextView) itemView.findViewById(R.id.tvVote);
            tvPopularty=(TextView) itemView.findViewById(R.id.tvPopularty);
            llAdapterMain=(LinearLayout)itemView.findViewById(R.id.llAdapterMain);
        }
    }

    @NonNull
    @Override
    public PopularMoviesAdapter.PMHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_movies,parent,false);

        return new PMHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMoviesAdapter.PMHolder holder, final int position) {

        PopularMovies pm=list.get(position);

        holder.tvTitle.setText(pm.getTitle());
        holder.tvDate.setText(pm.getRelease_date());
        holder.tvVote.setText(pm.getVote_average());
        holder.tvPopularty.setText(pm.getPopularity()+" Reviews");
        Glide.with(context).load(context.getString(R.string.image_load_url)+""+pm.getPoster_path()).into(holder.ivPoster);

        holder.llAdapterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MoviesDetailActivity.class).putExtra("details",list.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
