package com.cinema.unalzafer;

import com.cinema.unalzafer.Model.DetailModel;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<PopularMoviesResponses> getPopularMovies(@Query("api_key") String api_key,
                                                  @Query("page") int page);

    @GET("movie/upcoming")
    Call<PopularMoviesResponses> getUpcoming(@Query("api_key") String api_key,
                                             @Query("page") int page);

    @GET("movie/now_playing")
    Call<PopularMoviesResponses> getLatest(@Query("api_key") String api_key,
                                           @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<PopularMovies> getDetails(@Path("movie_id") int movie_id,
                                   @Query("api_key") String api_key);



}
