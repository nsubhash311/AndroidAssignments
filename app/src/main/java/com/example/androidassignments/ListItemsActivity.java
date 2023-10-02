package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ListItemsActivity extends AppCompatActivity {
    Button b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        b5 = findViewById(R.id.button5);
        b5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ListItemsActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );
    }

    protected  void onResume() {
        super.onResume();
        Log.i("Resume", "Calling OnResume function");
    }

    protected  void onStart() {
        super.onStart();
        Log.i("Start", "Calling OnStart function");

    }
    protected  void onPause() {
        super.onPause();
        Log.i("Pause", "Calling OnPause function");

    }
    protected  void onStop() {
        super.onStop();
        Log.i("Stop", "Calling OnStop function");

    }protected  void onDestroy() {
        super.onDestroy();
        Log.i("Destroy", "Calling OnDestroy function");
    }
    protected  void onSaveInstanceState(){
        Log.i("SaveInstanceState()","Calling OnSaveInstanceState function");
    }
    protected void onRestoreInstanceState(){
        Log.i("RestoreInstanceState", "Calling RestoreInstanceState function");
    }
}