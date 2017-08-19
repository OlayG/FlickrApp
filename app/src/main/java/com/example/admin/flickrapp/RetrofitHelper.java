package com.example.admin.flickrapp;

import com.example.admin.flickrapp.model.FlickrAppModel;
import com.example.admin.flickrapp.model.Item;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 8/17/2017.
 */

public class RetrofitHelper {
   // services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1
    public static final String BASE_URL = "http://api.flickr.com/";

    public static final String PATH = "services/feeds/photos_public.gne";
    public static final String QUERY_TAG = "kitten";
    public static final String QUERY_FORMAT = "json";
    public static final String QUERY_NOJSONCALLBACK = "1";

    public static Retrofit create(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<FlickrAppModel> getFlickrCall(){

        Retrofit retrofit = create();
        FlickrService flickrService = retrofit.create(FlickrService.class);

        return flickrService.getFlickrdata(QUERY_TAG, QUERY_FORMAT, QUERY_NOJSONCALLBACK);
    }

    public interface FlickrService {
        // services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1

        @GET(PATH)
        Call<FlickrAppModel> getFlickrdata(@Query("tag") String tag, @Query("format") String format,
                                 @Query("nojsoncallback") String nojsoncallback);
    }
}
