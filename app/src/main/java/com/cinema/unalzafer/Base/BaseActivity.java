package com.cinema.unalzafer.Base;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.cinema.unalzafer.Helper.RetrofitHelper;
import com.cinema.unalzafer.Listener.ResponsesBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements ResponsesBody {
    public RetrofitHelper retrofitHelper;
    public static short  LATEST_MOVIES=0;
    public static short POPULAR_MOVIES=1;
    public static short  UPCOMING_MOVIES=2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitHelper=new RetrofitHelper(getActivity(),this,"");
    }
    public Activity getActivity(){return this;}

    public List<String> getReadFavorite(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("settingsFile", MODE_PRIVATE);
        Gson gson = new Gson();
        String json =  sharedPreferences.getString("ReadNotificationList", "");
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return  gson.fromJson(json, type);
    }

    public void setReadFavorite(List<String> newslist){
        Gson gson = new Gson();
        String json = gson.toJson(newslist);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("settingsFile", MODE_PRIVATE);
        sharedPreferences.edit().putString("ReadNotificationList",json).apply();
    }
}
