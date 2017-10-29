package com.example.ankit.insticable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.SimpleResource;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ankit.insticable.R;
import com.example.ankit.insticable.RVAdapter;
import com.example.ankit.insticable.events;
import com.example.ankit.insticable.instistudent;

import java.util.List;

/**
 * Created by ankit on 18/10/17.
 * This class generates the list of buttons
 * corresponding to specific interests of the user
 * in Recycler View
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    /**
     * The Mystudent.
     */
    instistudent mystudent;
    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new Myadapter.
     *
     * @param student the student
     * @param context the context
     */
    public Myadapter(instistudent student, Context context) {
        this.mystudent = student;
        this.context = context;
    }


    /**
     * The type My view holder.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Mybutton.
         */
        Button mybutton;

        /**
         * Instantiates a new My view holder.
         *This class holds the button to be used in Recycler View
         * @param itemView the item view
         */
        MyViewHolder(View itemView) {
            super(itemView);
            mybutton = (Button) itemView.findViewById(R.id.mybutton);
        }
    }

    /**
     * It gives the size of list of interests of the user
     * @return Returns the size of list
     */
    @Override
    public int getItemCount() {
        return mystudent.getinterests().size();
    }

    /**
     * This method inflates interests_activity_buttons
     * and uses it in Recycler View
     * @param viewGroup
     * @param i
     * @return A instance of ViewHolder class
     */
    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.interests_activity_buttons, viewGroup, false);
        Myadapter.MyViewHolder pvh = new Myadapter.MyViewHolder(v);
        return pvh;
    }


    /**
     * This function actually binds the list of interests
     * with the activity
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(Myadapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.mybutton.setText(mystudent.getinterests().get(i));
        final String mystring = mystudent.getinterests().get(i);
        myViewHolder.mybutton.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(v.getContext(), chat_interface.class);
            intent.putExtra("mystudent",mystudent);
            intent.putExtra("id1",4);
            intent.putExtra("unique",mystring);
            context.startActivity(intent);
        }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
