package com.example.ankit.insticable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.net.URL;
import java.util.List;

import static android.R.attr.description;
import static android.R.attr.setupActivity;
import static android.R.attr.targetActivity;
import static com.example.ankit.insticable.R.id.image1;
import static java.security.AccessController.getContext;

/**
 * Created by ankit on 8/10/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<events> eventsList;
    Context context;
    public RVAdapter(List<events> eventsList, Context context){
        this.eventsList = eventsList;
        this.context=context;
    }



    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView date;
        ImageView image1;
        TextView time;
        TextView venue;

//        Button btnButton1;
        TextView description;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.time);
             venue = (TextView)itemView.findViewById(R.id.venue);
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            description=(TextView)itemView.findViewById(R.id.despe);
        }
        public ImageView getImage(){
         return image1;
        }
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main_stu, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        YoYo.with(Techniques.FlipInY).playOn(personViewHolder.cv);
        personViewHolder.name.setText(eventsList.get(i).getName());
        personViewHolder.date.setText("Date : " + eventsList.get(i).getDate());
        personViewHolder.time.setText("Time : " + eventsList.get(i).getTime());
        personViewHolder.venue.setText("Venue : " + eventsList.get(i).getVenue());
        personViewHolder.description.setText(eventsList.get(i).getDescription());
        String imageurl = eventsList.get(i).getPhotoUrl();
       Glide.with(context).load(imageurl).into((ImageView) personViewHolder.getImage());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
