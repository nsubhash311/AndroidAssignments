package com.example.androidassignments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }
        );
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,ListItemsActivity.class);
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