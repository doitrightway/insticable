package com.example.ankit.insticable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by ankit on 4/10/17.
 */

public class GesturecoActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;

    public void organizer(List<events> newevents, instistudent newstudent) {


        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        // Add all the images to the ViewFlipper
        for (int i = 0; i < newevents.size(); i++) {
            events event = newevents.get(i);
            LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutco);
            TextView date= (TextView) findViewById(R.id.dateco);
            date.setText(event.getDate());
            TextView venue= (TextView) findViewById(R.id.venueco);
            venue.setText(event.getVenue());
            TextView time = (TextView) findViewById(R.id.timeco);
            time.setText(event.getTime());
            mViewFlipper.addView(layout);
        }

        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public void create(View view){
        startActivity(new Intent(GesturecoActivity.this, creator.class));
    }

}
