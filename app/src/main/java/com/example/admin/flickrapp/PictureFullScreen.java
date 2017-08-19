package com.example.admin.flickrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureFullScreen extends AppCompatActivity {

    @BindView(R.id.ivFullScreenPhoto)
    ImageView ivFullScreenPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_full_screen);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        Picasso.with(this).load(intent.getStringExtra("pic"))
                .fit()
                .into(ivFullScreenPhoto);
    }
}
