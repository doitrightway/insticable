package com.example.ankit.insticable;

import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.List;

import static com.example.ankit.insticable.R.id.mybutton;

//import static android.R.attr.description;
//import static android.R.attr.setupActivity;
//import static android.R.attr.targetActivity;
//import static com.example.ankit.insticable.R.id.button;
//import static com.example.ankit.insticable.R.id.image1;
//import static com.example.ankit.insticable.R.id.mybutton;
//import static java.security.AccessController.getContext;

/**
 * Created by ankit on 8/10/17.
 * This class is used to display events of
 * user's interests in Recycler View
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    /**
     * The Events list.
     */
    List<events> eventsList;
    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Rv adapter.
     *
     * @param eventsList the events list
     * @param context    the context
     */
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    public RVAdapter(List<events> eventsList, Context context){
        this.eventsList = eventsList;
        this.context=context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("events");
    }


    /**
     * The type Person view holder.
     *This class holds the view items of the xml file
     *defining layout of the event page
     */
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Cv.
         */
        CardView cv;
        /**
         * The Name.
         */
        TextView name;
        /**
         * The Date.
         */
        TextView date;
        /**
         * The Image 1.
         */
        ImageView image1;
        /**
         * The Time.
         */
        TextView time;
        /**
         * The Venue.
         */
        TextView venue;

        /**
         * The Description.
         */
//        Button btnButton1;
        TextView description;

        TextView number;

        Button mybutton;

        /**
         * Instantiates a new Person view holder.
         * @param itemView the item view
         */
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.time);
             venue = (TextView)itemView.findViewById(R.id.venue);
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            description=(TextView)itemView.findViewById(R.id.despe);
            number=(TextView)itemView.findViewById(R.id.counter);
            mybutton=(Button)itemView.findViewById(R.id.counterbutton);
        }

        /**
         * Get image image view.
         *
         * @return the image view
         */
        public ImageView getImage(){
         return image1;
        }
    }

    /**
     * It gives the size of events list
     * @return Returns size of the events list
     */
    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    /**
     * It inflates the event page to be used in Recycler View
     * @param viewGroup
     * @param i
     * @return An instance of ViewHolder class
     */
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main_stu, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    /**
     * It actually binds the xml file layout
     * with contents received
     * @param personViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {

        final int[] mynum = {eventsList.get(i).getNumber()};
        final int k=i;
        personViewHolder.mybutton.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            mynum[0] += 1;
            personViewHolder.mybutton.setText("Liked");
            personViewHolder.mybutton.setEnabled(false);
            if(mynum[0]==1)
            {
                personViewHolder.number.setText(Integer.toString(mynum[0])+" person liked this");
            }
            else
            {
                personViewHolder.number.setText(Integer.toString(mynum[0])+" persons liked this");
            }
            mDatabaseReference.child(eventsList.get(k).getKey()).child("number").setValue(mynum[0]);
        }
        });
        YoYo.with(Techniques.FlipInY).playOn(personViewHolder.cv);
        personViewHolder.name.setText(eventsList.get(i).getName());
        personViewHolder.date.setText("Date : " + eventsList.get(i).getDate());
        personViewHolder.time.setText("Time : " + eventsList.get(i).getTime());
        personViewHolder.venue.setText("Venue : " + eventsList.get(i).getVenue());
        personViewHolder.description.setText(eventsList.get(i).getDescription());
        if(mynum[0]==0)
        {
            personViewHolder.number.setText("You can be the first person to like this, please hurry");
        }
        else if(mynum[0]==1)
        {
            personViewHolder.number.setText(Integer.toString(mynum[0])+" person liked this");
        }
        else
        {
            personViewHolder.number.setText(Integer.toString(mynum[0])+" persons liked this");
        }
        String imageurl = eventsList.get(i).getPhotoUrl();
       Glide.with(context).load(imageurl).into((ImageView) personViewHolder.getImage());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
