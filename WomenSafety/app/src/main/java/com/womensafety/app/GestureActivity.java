package com.womensafety.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GestureActivity extends AppCompatActivity {

    private GestureOverlayView gestureOverlayView = null;

    private GestureLibrary gestureLibrary = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gestures Setting");

        /*mGestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);

        if(!mGestureLibrary.load()){
            Toast.makeText(this, "Unable to load gestures", Toast.LENGTH_LONG).show();
        }*/
        //TODO: //handle mGestureLibrary if not loaded
        init(GestureActivity.this);
        GesturePerformListener gesturePerformListener = new GesturePerformListener(gestureLibrary);

        gestureOverlayView.addOnGesturePerformedListener(gesturePerformListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*@Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        ArrayList<Prediction> predictions = mGestureLibrary.recognize(gesture);
        if(predictions.size()>0 && predictions.get(0).score > 1){
            //gesture found
            String gestureName = predictions.get(0).name;
            Toast.makeText(this, "gestureName", Toast.LENGTH_LONG).show();
        }
    }*/
    /* Initialise class or instance variables. */
    private void init(Context context)
    {
        if(gestureLibrary == null)
        {
            // Load custom gestures from gesture.txt file.
            gestureLibrary = GestureLibraries.fromRawResource(context, R.raw.gestures);

            if(!gestureLibrary.load())
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Custom gesture file load failed.");
                alertDialog.show();

                finish();
            }
        }

        if(gestureOverlayView == null)
        {
            gestureOverlayView = (GestureOverlayView)findViewById(R.id.gestureView);
        }
    }
}