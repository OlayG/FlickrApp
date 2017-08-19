package com.example.admin.flickrapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.flickrapp.model.FlickrAppModel;
import com.example.admin.flickrapp.model.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    RecyclerView.ItemAnimator itemAnimator;
    FlickrAdapter flickrAdapter;
    ArrayList<Item> itemList;
    @BindView(R.id.rvPhotoFeed)
    RecyclerView rvPhotoFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        itemAnimator = new DefaultItemAnimator();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvPhotoFeed.setLayoutManager(new GridLayoutManager(this, 3));
        }else{

            rvPhotoFeed.setLayoutManager(new GridLayoutManager(this, 2));
        }
        rvPhotoFeed.setItemAnimator(itemAnimator);


        retrofit2.Call<FlickrAppModel> itemCall = RetrofitHelper.getFlickrCall();
        itemCall.enqueue(new Callback<FlickrAppModel>() {
            @Override
            public void onResponse(Call<FlickrAppModel> call, Response<FlickrAppModel> response) {

                itemList = (ArrayList<Item>) response.body().getItems();
                flickrAdapter = new FlickrAdapter(itemList);
                rvPhotoFeed.setAdapter(flickrAdapter);
                flickrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FlickrAppModel> call, Throwable t) {

            }
        });


    }


}
