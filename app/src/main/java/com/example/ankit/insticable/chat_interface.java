package com.example.ankit.insticable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 14/10/17.
 */

public class chat_interface extends AppCompatActivity {


    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    private String mUsername;
    private static final int RC_PHOTO_PICKER= 2;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    int param;
    instistudent mystudent = new instistudent();
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_interface);
        Intent intent = getIntent();
        mystudent = (instistudent) intent.getExtras().getSerializable("mystudent");
        String hostel = mystudent.getHostel();
        String department=mystudent.getDepartment();
        String degree = mystudent.getDegree();
        param = (int) intent.getExtras().getInt("id1");
        mUsername = mystudent.getName();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        if(param==1)
        {
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("hostel").child(hostel);
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("hostel").child(hostel);
        }
        else if(param==2)
        {
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department);
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department);;

        }
        else if(param==3)
        {
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department).child("Degree").child(degree);
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department).child("Degree").child(degree);
        }
        else if(param==4)
        {
            String imp= (String) intent.getExtras().getString("unique");
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department).child("Degree").
                    child(degree).child("interests").child(imp);
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("hostel").child(hostel).child("Department").child(department).child("Degree").
                    child(degree).child("interests").child(imp);
        }
        else if(param==-1)
        {
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("Department").child(department);
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("Department").child(department);
        }
        else if(param==-2)
        {
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("Department").child(department).child("Degree").child(degree);
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("Department").child(department).child("Degree").child(degree);
        }
        else if(param==-3)
        {
            String imp= (String) intent.getExtras().getString("unique");
            mDatabaseReference = mFirebaseDatabase.getReference().child("groupchat").child("Department").child(department).child("Degree").
                    child(degree).child("interests").child(imp);
            mChatPhotosStorageReference = mFirebaseStorage.getReference().child("groupchat").child("Department").child(department).child("Degree").
                    child(degree).child("interests").child(imp);
        }



        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        List<MessageInstance> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                // TODO: Fire an intent to show an image picker
            }
        });

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                // Clear input box
                MessageInstance friendlyMessage = new MessageInstance(mMessageEditText.getText().toString(), mUsername, null);
                mDatabaseReference.push().setValue(friendlyMessage);
                mMessageEditText.setText("");
            }
        });
        onSignedInInitialize(mUsername);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK)
        {
            Uri selectedImageUri= data.getData();
            StorageReference photoRef = mChatPhotosStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl=taskSnapshot.getDownloadUrl();
                    MessageInstance friendlyMessage = new MessageInstance(null,mUsername,downloadUrl.toString());
                    mDatabaseReference.push().setValue(friendlyMessage);
                }
            });
        }

    }


    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();
    }
//    private void onSignedOutCleanup(){
//        mUsername= ANONYMOUS;
//        mMessageAdapter.clear();
//        detachDatabaseReadListener();
//    }
    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MessageInstance friendlyMessage = dataSnapshot.getValue(MessageInstance.class);
                    mMessageAdapter.add(friendlyMessage);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }


//    private void detachDatabaseReadListener() {
//        if (mChildEventListener != null) {
//            mDatabaseReference.removeEventListener(mChildEventListener);
//            mChildEventListener=null;
//        }
//    }
}
