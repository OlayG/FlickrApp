package com.example.admin.flickrapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.flickrapp.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 8/17/2017.
 */

public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.ViewHolder>{

    public static final String TAG = "FlickrAdapter";
    ArrayList<Item> items = new ArrayList<>();

    public FlickrAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_thumbnail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item item = items.get(position);

        holder.tvLocation.setText(null);
        Picasso.with(holder.itemView.getContext()).load(item.getMedia().getM())
                .fit()
                .into(holder.ivPhoto);



        holder.ivPhoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Image View");
                builder.setItems(new CharSequence[]
                                {"Show Full Image", "Show Small Image"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:

                                        Intent in = new Intent(v.getContext(), PictureFullScreen.class);
                                        in.putExtra("pic", item.getMedia().getM());
                                        v.getContext().startActivity(in);

                                        break;
                                    case 1:
                                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                        AlertDialog ad = builder.create();
                                        LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View dialogLayout = inflater.inflate(R.layout.flickr_dialog_layout, null);
                                        ad.setView(dialogLayout);
                                        ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        //((ImageView) dialogLayout.findViewById(R.id.ivDialogPhoto)).setImageResource(R.mipmap.ic_launcher);
                                        Log.d(TAG, "onClick: " + item.getMedia().getM());
                                        Picasso.with(
                                                v.getContext())
                                                .load(item.getMedia().getM())
                                                .into((ImageView) dialogLayout.findViewById(R.id.ivDialogPhoto));
                                        ad.show();

                                        break;
                                }
                            }
                        });
                builder.create().show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPhoto, ivDialogPhoto;
        TextView tvLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            ivDialogPhoto = (ImageView) itemView.findViewById(R.id.ivDialogPhoto);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
