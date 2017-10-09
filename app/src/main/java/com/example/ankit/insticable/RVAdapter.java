package com.example.ankit.insticable;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import static android.R.attr.description;
import static android.R.attr.setupActivity;

/**
 * Created by ankit on 8/10/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<events> eventsList;

    public RVAdapter(List<events> eventsList){
        this.eventsList = eventsList;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView date;
        ImageView image;
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
            //image = (ImageView)itemView.findViewById(R.id.image);
            venue = (TextView)itemView.findViewById(R.id.venue);
//            btnButton1= (Button)itemView.findViewById(R.id.getinfo);
            description=(TextView)itemView.findViewById(R.id.despe);
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
        personViewHolder.date.setText(eventsList.get(i).getDate());
        personViewHolder.time.setText(eventsList.get(i).getTime());
        personViewHolder.venue.setText(eventsList.get(i).getVenue());
        personViewHolder.description.setText(eventsList.get(i).getDescription());
        //personViewHolder.image.setImageResource(eventsList.get(i).photoId);
//        personViewHolder.btnButton1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                setupActivity
//            }
//        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
