package com.example.ankit.insticable;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class Create_event extends Activity {

    List<String> intereststags = new ArrayList<>();
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotoStorageReferrence;
    private DatabaseReference meventReference;
    private FirebaseDatabase mFirebaseDatabase;
    private ProgressBar mProgressBar;
    private Button eventaddimage;
    private String addedimageUrl = new String();
    private ImageView imageevent;
    private Uri selectedImageUri;
    private TextInputEditText mDateinput;
    private TextInputEditText mTimeinput;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private TimePickerDialog.OnTimeSetListener mTimeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotoStorageReferrence = mFirebaseStorage.getReference().child("event_photos");
        meventReference = mFirebaseDatabase.getReference().child("events");
        eventaddimage = (Button) findViewById(R.id.addimage);
        setContentView(R.layout.activity_main_cre);
        mDateinput = (TextInputEditText) findViewById(R.id.datecre);
mTimeinput=(TextInputEditText)findViewById(R.id.timecre);
//       initializing the uri with null string
        selectedImageUri = Uri.parse("");
        addedimageUrl = new String();


        //    set calendar values in edit text
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDateinput.setText(date);
            }
        };


        mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(((hourOfDay/12)==1)&& (hourOfDay%12)!=0) {
                    String time =  (hourOfDay-12) + " : " + minute + " pm";
                    mTimeinput.setText(time);
                }
                else if(((hourOfDay/12)==0)&& (hourOfDay%12)==0) {
                    String time =  (hourOfDay+12) + " : " + minute + " am";
                    mTimeinput.setText(time);
                }
                else if(((hourOfDay/12)==1)&& (hourOfDay%12)==0) {
                    String time =  hourOfDay + " : " + minute + " pm";
                    mTimeinput.setText(time);
                }
                else {
                    String time =  hourOfDay + " : " + minute + " am";
                    mTimeinput.setText(time);
                }
            }
        };


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

    }

//  datepicker on clicking on date text
    public void add_date(View v){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog= new DatePickerDialog (Create_event.this, mDateListener, year,month,day);
        dialog.show();



    }
//    timepicker on clicking on time text
    public void add_time(View v) {
        Calendar cal = Calendar.getInstance();
        int hour_x = cal.get(Calendar.HOUR_OF_DAY);
        int min_x = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(Create_event.this, mTimeListener, hour_x, min_x, false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();

//            to dislay image in create event page
            InputStream inputstream;
            try {
                inputstream = getContentResolver().openInputStream(selectedImageUri);

                Bitmap image = BitmapFactory.decodeStream(inputstream);

                imageevent = (ImageView) findViewById(R.id.createeventimage);

                imageevent.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, selectedImageUri.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        student=null;
        deletelistener();
    }

    private void deletelistener() {

    }


    public void add_image(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 2);

    }

    public void cricket_cre(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("cricket");
        } else {
            intereststags.remove("cricket");
        }
    }

    public void football_cre(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("football");
        } else {
            intereststags.remove("football");
        }
    }

    public void tennis_cre(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("tennis");
        } else {
            intereststags.remove("tennis");
        }
    }

    public void squash_cre(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("squash");
        } else {
            intereststags.remove("squash");
        }
    }

    public void swimming_cre(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("swimming");
        } else {
            intereststags.remove("swimming");
        }
    }

    public void carrom_cre(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("carrom");
        } else {
            intereststags.remove("carrom");
        }
    }

    public void chess_cre(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("chess");
        } else {
            intereststags.remove("chess");
        }
    }

    public void music_cre(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            intereststags.add("music");
        } else {
            intereststags.remove("music");
        }
    }


    public void push_cre(View view) {
        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        TextInputEditText name = (TextInputEditText) findViewById(R.id.namecre);
        TextInputEditText date = (TextInputEditText) findViewById(R.id.datecre);
        TextInputEditText venue = (TextInputEditText) findViewById(R.id.venuecre);
        TextInputEditText time = (TextInputEditText) findViewById(R.id.timecre);
        TextInputEditText description = (TextInputEditText) findViewById(R.id.descriptioncre);
        final events event = new events();
        event.setName(name.getText().toString());
        event.setDate(date.getText().toString());
        event.setTime(time.getText().toString());
        event.setVenue(venue.getText().toString());
        event.setDescription(description.getText().toString());
        event.setInterests(intereststags);

        //  checking if image was given
        if (!(selectedImageUri.toString().equals(""))) {
            //            send image to storage
            StorageReference photoRef = mChatPhotoStorageReferrence.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    addedimageUrl = downloadUrl.toString();
                    //      saving imageurl in the event photourl child.
                    event.setPhotoUrl(addedimageUrl);

                    meventReference.push().setValue(event);
                    finish();
                }
            });
        }
// the same thing thing had to be written in if as well else and not outside because it takes some time to upload
// the image.
        else {
            //      saving imageurl in the event photourl child
            event.setPhotoUrl(addedimageUrl);
            meventReference.push().setValue(event);
            finish();
        }
    }
}
