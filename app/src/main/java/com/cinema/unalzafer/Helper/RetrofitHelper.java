package com.cinema.unalzafer.Helper;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Toast;

import com.cinema.unalzafer.ApiInterface;
import com.cinema.unalzafer.Listener.ResponsesBody;
import com.cinema.unalzafer.Model.DetailModel;
import com.cinema.unalzafer.Model.PopularMovies;
import com.cinema.unalzafer.Model.PopularMoviesResponses;
import com.cinema.unalzafer.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private ApiInterface apiInterface;
    //private ProgressDialog dialog;
    private Dialog dialog;
    Activity activity;
    ResponsesBody intfBodySend;

    String API_URL="https://api.themoviedb.org/3/";
    String API_KEY="cd4a0be78769def38b9a6ee98a429086";

    public RetrofitHelper(Activity activity,ResponsesBody intfBodySend,String API_URL){
        this.intfBodySend=intfBodySend;
        this.activity=activity;
        if (API_URL!=null && !API_URL.equals("")){
            this.API_URL=API_URL;
        }

        Retrofit retrofit = new Retrofit.Builder().
                client(getClient()).
                baseUrl(this.API_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        apiInterface = retrofit.create(ApiInterface.class);
        //dialog =ShowDialogHelper.animateDialog(activity);

    }
    private Activity getActivity(){return activity;}

    private  <T> void callRetrofit(Call<T> call, final int i) {
            //ShowDialogHelper.dialogShow(getActivity(),dialog);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                //ShowDialogHelper.dialogDismiss(getActivity(),dialog);

                if (response!=null && response.body()!=null)
                {
                    intfBodySend.getResponseBody(response.body(),i);
                }
                else Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                //ShowDialogHelper.dialogDismiss(getActivity(), dialog);
                    Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();


            }
        });

    }
    public static OkHttpClient getClient() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(1, TimeUnit.MINUTES);
        b.writeTimeout(1, TimeUnit.MINUTES);
        return b.build();
    }



    public void getPopularMovies(int i,int page ){
        Call<PopularMoviesResponses> call=apiInterface.getPopularMovies(API_KEY,page);
        callRetrofit(call,i);
    }
    public void getUpcomingMovies(int i,int page ){
        Call<PopularMoviesResponses> call=apiInterface.getUpcoming(API_KEY,page);
        callRetrofit(call,i);
    }

    public void getLatestMovies(int i,int page){
        Call<PopularMoviesResponses> call=apiInterface.getLatest(API_KEY,page);
        callRetrofit(call,i);
    }
    public void getDetailMovies(int i,int movieId){
        Call<PopularMovies> call=apiInterface.getDetails(movieId,API_KEY);
        callRetrofit(call,i);
    }
}
